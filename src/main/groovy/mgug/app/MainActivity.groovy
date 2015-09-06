package mgug.app

import android.app.AlertDialog
import android.app.ListActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.arasthel.swissknife.annotations.OnUIThread
import groovy.transform.CompileStatic
import mgug.app.adapter.TopicAdapter
import mgug.app.domain.Topic
import rx.Observable
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.functions.Action1
import rx.schedulers.Schedulers

@CompileStatic
class MainActivity extends ListActivity {

    TopicAdapter topicAdapter

    @Override
    void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
        topicAdapter = new TopicAdapter(this, R.layout.topic_item)
        setListAdapter(topicAdapter)
        loadTopicList()
    }

    @Override
    boolean onCreateOptionsMenu(Menu menu) {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    @Override
    boolean onOptionsItemSelected(MenuItem item) {
        int id = item.itemId

        if (id == R.id.action_about) {
            showAbout()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    @OnUIThread
    void showAbout() {
         new AlertDialog.Builder(this)
            .setIcon(R.drawable.ic_launcher)
            .setTitle(R.string.app_name)
            .setView(layoutInflater.inflate(R.layout.about, null, false))
            .create()
            .show()
    }

    void loadTopicList() {
        Observable.create(this.&doItInBackground as Observable.OnSubscribe)
                  .subscribeOn(Schedulers.newThread())
                  .observeOn(AndroidSchedulers.mainThread())
                  .subscribe(new Action1<Topic>() {
                    @Override
                    void call(Topic topic) {
                        topicAdapter.add(topic)
                    }
                  })
    }

    void doItInBackground(final Subscriber subscriber) {
        (0..10).each {
            subscriber.onNext(
                new Topic(author: 'John Doe', description: 'Good topic 1', checked: false, votes: 20)
            )
        }
        subscriber.onCompleted()
    }

}
