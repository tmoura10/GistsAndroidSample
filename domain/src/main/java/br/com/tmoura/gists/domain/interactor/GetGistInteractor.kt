package br.com.tmoura.gists.domain.interactor

import br.com.tmoura.gists.domain.model.Gist
import io.reactivex.Single

interface GetGistInteractor : Interactor<GetGistInteractor.Params, Single<Gist>> {

    data class Params(val id: String)

}