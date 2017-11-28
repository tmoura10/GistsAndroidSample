package br.com.tmoura.gists.domain.interactor

import br.com.tmoura.gists.domain.SchedulersProvider
import br.com.tmoura.gists.domain.model.Gist
import br.com.tmoura.gists.domain.repository.GistRepository
import io.reactivex.Completable
import javax.inject.Inject

class StarGistInteractor @Inject constructor(
        private val gistRepository: GistRepository,
        private val schedulersProvider: SchedulersProvider
): Interactor<StarGistInteractor.Params, Completable> {

    override fun execute(params: Params): Completable {
        return gistRepository.starGist(gist = params.gist)
                .compose(schedulersProvider.buildTransformer<Any>())
    }

    data class Params(val gist: Gist)
}