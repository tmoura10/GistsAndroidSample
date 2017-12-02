package br.com.tmoura.gists.domain.interactor.impl

import br.com.tmoura.gists.domain.SchedulersProvider
import br.com.tmoura.gists.domain.interactor.GetGistInteractor
import br.com.tmoura.gists.domain.model.Gist
import br.com.tmoura.gists.domain.repository.GistRepository
import io.reactivex.Single
import javax.inject.Inject

class GetGistInteractorImpl @Inject constructor(
        private val gistRepository: GistRepository,
        private val schedulersProvider: SchedulersProvider
) : GetGistInteractor {

    override fun execute(params: GetGistInteractor.Params): Single<Gist> {
        return gistRepository.getGist(params.id).compose(schedulersProvider.buildTransformer())
    }

}