package br.com.tmoura.gists.data.remote

import br.com.tmoura.gists.data.dataset.GistRemoteDataSet
import br.com.tmoura.gists.data.remote.mapper.toDomain
import br.com.tmoura.gists.domain.model.Gist
import io.reactivex.Single
import javax.inject.Inject

class GistRemoteDataSetImpl @Inject constructor(
        val api: GistApi
): GistRemoteDataSet {

    companion object {
        private val GISTS_PER_PAGE = 50
    }

    override fun getGists(loadedItemsCount: Int): Single<List<Gist>> {
        val page = (loadedItemsCount / GISTS_PER_PAGE) + 1
        val gists = api.getGists(page, GISTS_PER_PAGE)
        return gists.map { items -> items.map { it.toDomain() } }
    }

    override fun getGist(id: String): Single<Gist> {
        val gist = api.getGist(id = id)
        return gist.map { it.toDomain() }
    }
}