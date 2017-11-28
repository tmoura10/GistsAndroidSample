package br.com.tmoura.gists.domain.repository

import br.com.tmoura.gists.domain.model.Gist
import io.reactivex.Single

interface GistRepository {
    fun getGists(loadedItemsCount: Int): Single<List<Gist>>
    fun getFavoriteGists(): Single<List<Gist>>
    fun getGist(id: String): Single<Gist>
}