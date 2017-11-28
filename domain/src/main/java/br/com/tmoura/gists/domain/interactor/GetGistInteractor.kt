package br.com.tmoura.gists.domain.interactor

import br.com.tmoura.gists.domain.SchedulersProvider
import br.com.tmoura.gists.domain.model.Gist
import br.com.tmoura.gists.domain.repository.GistRepository
import io.reactivex.Single
import javax.inject.Inject

class GetGistInteractor @Inject constructor(
        private val gistRepository: GistRepository,
        private val schedulersProvider: SchedulersProvider
) : Interactor<GetGistInteractor.Params, Single<Gist>> {


    override fun execute(params: Params): Single<Gist> {
        return gistRepository.getGist(params.id).compose(schedulersProvider.buildTransformer())
    }

    data class Params(val id: String)
}