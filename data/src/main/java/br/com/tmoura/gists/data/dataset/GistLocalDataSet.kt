package br.com.tmoura.gists.data.dataset

import br.com.tmoura.gists.domain.model.Gist
import io.reactivex.Completable
import io.reactivex.Single

interface GistLocalDataSet {
    fun getStarredGists(): Single<List<Gist>>
    fun markStarredGists(gists: List<Gist>): Single<List<Gist>>
    fun starGist(gist: Gist): Completable
}