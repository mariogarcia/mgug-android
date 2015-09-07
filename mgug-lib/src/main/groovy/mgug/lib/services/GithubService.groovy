package mgug.lib.services

import mgug.lib.domain.Repository
import retrofit.http.GET
import retrofit.http.Path
import rx.Observable

interface GithubService {

    @GET('/users/{username}/repos')
    Observable<List<Repository>> findAllRepositoriesOf(@Path('username') String username)

}