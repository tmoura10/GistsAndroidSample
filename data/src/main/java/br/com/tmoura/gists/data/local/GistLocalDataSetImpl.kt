package br.com.tmoura.gists.data.local

import br.com.tmoura.gists.data.dataset.GistLocalDataSet
import br.com.tmoura.gists.domain.model.Gist
import io.reactivex.Completable
import io.reactivex.Single

class GistLocalDataSetImpl : GistLocalDataSet {
    override fun getStarredGists(): Single<List<Gist>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun markStarredGists(gists: List<Gist>): Single<List<Gist>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun starGist(gist: Gist): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}