package br.com.tmoura.gists.domain.interactor

import br.com.tmoura.gists.domain.model.Gist
import io.reactivex.Completable

interface StarGistInteractor : Interactor<StarGistInteractor.Params, Completable> {
    data class Params(val gist: Gist)
}