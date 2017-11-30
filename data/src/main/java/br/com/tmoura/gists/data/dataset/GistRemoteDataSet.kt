package br.com.tmoura.gists.data.dataset

import br.com.tmoura.gists.domain.model.Gist
import io.reactivex.Single

interface GistRemoteDataSet {
    fun getGists(loadedItemsCount: Int): Single<List<Gist>>
    fun getGist(id: String): Single<Gist>
}