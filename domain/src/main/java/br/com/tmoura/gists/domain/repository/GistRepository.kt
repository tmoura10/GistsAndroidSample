package br.com.tmoura.gists.domain.repository

import br.com.tmoura.gists.domain.model.Gist
import io.reactivex.Completable
import io.reactivex.Single

interface GistRepository {
    fun getGists(loadedItemsCount: Int): Single<List<Gist>>
    fun getStarredGists(): Single<List<Gist>>
    fun getGist(id: String): Single<Gist>
    fun starGist(gist: Gist): Completable
}