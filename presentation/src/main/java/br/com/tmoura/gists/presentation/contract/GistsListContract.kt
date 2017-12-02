package br.com.tmoura.gists.presentation.contract

import br.com.tmoura.gists.presentation.contract.commons.BasePresenter
import br.com.tmoura.gists.presentation.contract.commons.ErrorHandlerView
import br.com.tmoura.gists.presentation.contract.commons.LoaderView
import br.com.tmoura.gists.presentation.model.GistItemViewModel

interface GistsListContract {

    interface Presenter : BasePresenter {
        fun loadGists(loadedItemsCount: Int = 0)
    }

    interface View : LoaderView, ErrorHandlerView {
        fun displayGists(gistsViewModel: List<GistItemViewModel>)
    }

}