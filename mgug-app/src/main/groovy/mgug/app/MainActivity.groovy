package mgug.app

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.arasthel.swissknife.annotations.OnUIThread
import mgug.app.adapter.RepositoryAdapter
import mgug.app.services.Services
import mgug.app.widget.CustomListActivity
import mgug.app.widget.Widgets
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class MainActivity extends CustomListActivity<RepositoryAdapter> {

    @Override
    void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
        setListAdapter(new RepositoryAdapter(this, R.layout.repository_item))
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
        showDialogWithLayout(R.layout.about){
            icon  = R.drawable.ic_launcher
            title = R.string.app_name
        }
    }

    void loadTopicList() {
        use(Services) {
            githubService.findAllRepositoriesOf("mariogarcia")
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(arrayAdapter.&addAll)
        }
    }

}
