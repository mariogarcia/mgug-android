package mgug.app

import android.app.AlertDialog
import android.app.ListActivity
import android.os.AsyncTask
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import com.arasthel.swissknife.annotations.OnBackground
import com.arasthel.swissknife.annotations.OnUIThread
import groovy.transform.CompileStatic
import mgug.app.adapter.TopicAdapter
import mgug.app.domain.Topic

@CompileStatic
class MainActivity extends ListActivity {
      
    @Override
    void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
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
        AsyncTask<Object,Integer, List<Topic>> task = new AsyncTask<Object,Integer, List<Topic>> () {
            @Override
            protected List<Topic> doInBackground(Object[] params) {
                List<Topic> topics = [
                    new Topic(author: 'John Doe', description: 'Good topic 1', checked: false, votes: 20),
                    new Topic(author: 'John Doe', description: 'Good topic 2', checked: false, votes: 0)
                ]
            }
            @Override
            protected void onPostExecute(List<Topic> list) {
                ArrayAdapter<Topic> adapter = new TopicAdapter(MainActivity.this, R.layout.topic_item)
                adapter.addAll(list)
                setListAdapter(adapter)
            }
        }

        task.execute()
    }
    
}
