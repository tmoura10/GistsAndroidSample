package br.com.tmoura.gists.data.repository

import br.com.tmoura.gists.data.dataset.GistLocalDataSet
import br.com.tmoura.gists.data.dataset.GistRemoteDataSet
import br.com.tmoura.gists.domain.model.Gist
import br.com.tmoura.gists.domain.repository.GistRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class GistRepositoryImpl @Inject constructor(
        private val localDataSet: GistLocalDataSet,
        private val remoteDataSet: GistRemoteDataSet
) : GistRepository {

    override fun getGists(loadedItemsCount: Int): Single<List<Gist>> {
        return remoteDataSet
                .getGists(loadedItemsCount = loadedItemsCount)
    }

    override fun getStarredGists(): Single<List<Gist>> {
        return localDataSet.getStarredGists()
    }

    override fun getGist(id: String): Single<Gist> {
        return remoteDataSet.getGist(id)
    }

    override fun starGist(gist: Gist): Completable {
        return localDataSet.starGist(gist)
    }
}