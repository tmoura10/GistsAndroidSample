package br.com.tmoura.gists.domain.interactor.impl

import br.com.tmoura.gists.domain.SchedulersProvider
import br.com.tmoura.gists.domain.interactor.GetGistsInteractor
import br.com.tmoura.gists.domain.model.Gist
import br.com.tmoura.gists.domain.repository.GistRepository
import io.reactivex.Single
import javax.inject.Inject

class GetGistsInteractorImpl @Inject constructor(
        private val gistRepository: GistRepository,
        private val schedulersProvider: SchedulersProvider)
    : GetGistsInteractor
{
    override fun execute(params: GetGistsInteractor.Params): Single<List<Gist>> {
        return gistRepository
                .getGists(params.loadedItemsCount)
                .compose(schedulersProvider.buildTransformer())
    }

}