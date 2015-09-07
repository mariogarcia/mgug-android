package mgug.app

import android.os.Bundle
import com.arasthel.swissknife.annotations.OnUIThread
import mgug.app.adapter.RepositoryAdapter
import mgug.app.services.Services
import mgug.app.widget.CustomListActivity
import mgug.lib.asts.OnOptionsItemSelected
import mgug.lib.asts.OptionsMenu
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

@OptionsMenu(R.menu.menu_main)
class MainActivity extends CustomListActivity<RepositoryAdapter> {

    @Override
    void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
        setListAdapter(new RepositoryAdapter(this, R.layout.repository_item))
        loadTopicList()
    }

    @OnUIThread
    @OnOptionsItemSelected(R.id.action_about)
    void showAbout() {
        showDialogWithLayout(R.layout.about){
            icon  = R.drawable.ic_launcher
            title = getString(R.string.app_name)
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
