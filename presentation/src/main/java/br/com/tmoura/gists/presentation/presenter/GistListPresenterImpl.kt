package br.com.tmoura.gists.presentation.presenter

import br.com.tmoura.gists.domain.interactor.GetGistsInteractor
import br.com.tmoura.gists.domain.model.Gist
import br.com.tmoura.gists.presentation.contract.GistsListContract
import br.com.tmoura.gists.presentation.mapper.toViewModel
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class GistListPresenterImpl @Inject constructor(
        private val view: GistsListContract.View,
        private val getGists: GetGistsInteractor
) : GistsListContract.Presenter {

    private var disposable: Disposable? = null

    override fun register() {
        loadGists()
    }

    override fun unRegister() {
        disposable?.let { if (!it.isDisposed) it.dispose() }
    }

    override fun loadGists(loadedItemsCount: Int) {
        view.displayLoader()
        disposable = getGists.execute(GetGistsInteractor.Params(loadedItemsCount = loadedItemsCount))
                .subscribeBy(
                        onError = this::displayError,
                        onSuccess = this::displayGists
                )
    }

    private fun displayError(error: Throwable) {
        view.hideLoader()
        view.displayError(error)
    }

    private fun displayGists(gists: List<Gist>) {
        val gistsViewModel = gists.map { it.toViewModel() }
        view.hideLoader()
        view.displayGists(gistsViewModel)
    }
}