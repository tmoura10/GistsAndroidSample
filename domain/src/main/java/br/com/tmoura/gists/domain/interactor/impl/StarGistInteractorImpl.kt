package br.com.tmoura.gists.domain.interactor.impl

import br.com.tmoura.gists.domain.SchedulersProvider
import br.com.tmoura.gists.domain.interactor.StarGistInteractor
import br.com.tmoura.gists.domain.repository.GistRepository
import io.reactivex.Completable
import javax.inject.Inject

class StarGistInteractorImpl @Inject constructor(
        private val gistRepository: GistRepository,
        private val schedulersProvider: SchedulersProvider
) : StarGistInteractor {

    override fun execute(params: StarGistInteractor.Params): Completable {
        return gistRepository.starGist(gist = params.gist)
                .compose(schedulersProvider.buildTransformer<Any>())
    }


}