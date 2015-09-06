package mgug.app.services

import android.content.Context
import mgug.app.R
import retrofit.RestAdapter

class Services {

    static GithubService getGithubService(final Context ctx) {
        return new RestAdapter.Builder()
            .setEndpoint(ctx.getString(R.string.api_host))
            .build()
            .create(GithubService)
    }


}