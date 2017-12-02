package br.com.tmoura.gists.domain.interactor

import br.com.tmoura.gists.domain.model.Gist
import io.reactivex.Single

interface GetGistsInteractor : Interactor<GetGistsInteractor.Params, Single<List<Gist>>> {

    data class Params(val loadedItemsCount: Int)
}