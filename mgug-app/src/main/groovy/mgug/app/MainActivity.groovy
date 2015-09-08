package mgug.app

import android.os.Bundle
import com.arasthel.swissknife.annotations.OnUIThread
import fnz.data.Function
import fnz.data.Try
import groovy.transform.CompileStatic
import mgug.app.adapter.RepositoryAdapter
import mgug.app.widget.CustomListActivity
import mgug.lib.asts.OnOptionsItemSelected
import mgug.lib.asts.OptionsMenu
import mgug.lib.domain.Repository
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
        executeSafely()
    }

    @OnUIThread
    @OnOptionsItemSelected(R.id.action_about)
    void showAbout() {
        showDialogWithLayout(R.layout.about){
            icon  = R.drawable.ic_launcher
            title = getString(R.string.app_name)
        }
    }

    void executeSafely() {
        Try execution =
            Try("mariogarcia", this.&loadTopicList as Function)

        if (execution.isFailure()) {
            showMessage(R.string.error)
        }
    }

    void loadTopicList(String username) {
        githubService.findAllRepositoriesOf(username)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(arrayAdapter.&addAll as Action1<List<Repository>>)
    }

}
