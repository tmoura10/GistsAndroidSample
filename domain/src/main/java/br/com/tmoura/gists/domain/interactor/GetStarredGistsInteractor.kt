package br.com.tmoura.gists.domain.interactor

import br.com.tmoura.gists.domain.SchedulersProvider
import br.com.tmoura.gists.domain.model.Gist
import br.com.tmoura.gists.domain.repository.GistRepository
import io.reactivex.Single
import javax.inject.Inject

class GetStarredGistsInteractor @Inject constructor(
        val gistRepository: GistRepository,
        val schedulersProvider: SchedulersProvider)
    : Interactor<Unit, Single<List<Gist>>> {

    override fun execute(params: Unit): Single<List<Gist>> {
        return gistRepository.getStarredGists().compose(schedulersProvider.buildTransformer())
    }
}