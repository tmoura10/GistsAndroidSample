package br.com.tmoura.gists.domain.interactor

import br.com.tmoura.gists.domain.model.Gist
import io.reactivex.Single

interface GetStarredGistsInteractor : Interactor<Unit, Single<List<Gist>>>