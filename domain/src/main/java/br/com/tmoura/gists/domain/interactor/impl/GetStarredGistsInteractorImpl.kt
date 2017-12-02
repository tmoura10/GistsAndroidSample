package br.com.tmoura.gists.domain.interactor.impl

import br.com.tmoura.gists.domain.SchedulersProvider
import br.com.tmoura.gists.domain.interactor.GetStarredGistsInteractor
import br.com.tmoura.gists.domain.model.Gist
import br.com.tmoura.gists.domain.repository.GistRepository
import io.reactivex.Single
import javax.inject.Inject

class GetStarredGistsInteractorImpl @Inject constructor(
        private val gistRepository: GistRepository,
        private val schedulersProvider: SchedulersProvider)
    : GetStarredGistsInteractor {

    override fun execute(params: Unit): Single<List<Gist>> {
        return gistRepository.getStarredGists().compose(schedulersProvider.buildTransformer())
    }
}