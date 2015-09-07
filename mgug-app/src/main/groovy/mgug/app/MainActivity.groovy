package mgug.app

import android.os.Bundle
import com.arasthel.swissknife.annotations.OnUIThread
import groovy.transform.CompileStatic
import mgug.app.adapter.RepositoryAdapter
import mgug.lib.domain.Repository
import mgug.app.widget.CustomListActivity
import mgug.lib.asts.OnOptionsItemSelected
import mgug.lib.asts.OptionsMenu
import rx.android.schedulers.AndroidSchedulers
import rx.functions.Action1
import rx.schedulers.Schedulers

@CompileStatic
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
        githubService.findAllRepositoriesOf("mariogarcia")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(arrayAdapter.&addAll as Action1<List<Repository>>)
    }

}
