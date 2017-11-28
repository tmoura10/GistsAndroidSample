package br.com.tmoura.gists.domain.interactor

import br.com.tmoura.gists.domain.SchedulersProvider
import br.com.tmoura.gists.domain.model.Gist
import br.com.tmoura.gists.domain.repository.GistRepository
import io.reactivex.Single
import javax.inject.Inject

class GetGistsInteractor @Inject constructor(
        private val gistRepository: GistRepository,
        private val schedulersProvider: SchedulersProvider)
    : Interactor<GetGistsInteractor.Params, Single<List<Gist>>>
{
    override fun execute(params: Params): Single<List<Gist>> {
        return gistRepository
                .getGists(params.loadedItemsCount)
                .compose(schedulersProvider.buildTransformer())
    }

    data class Params(val loadedItemsCount: Int)
}