package br.com.tmoura.gists.view

import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.tmoura.gists.R
import br.com.tmoura.gists.presentation.contract.GistsListContract
import br.com.tmoura.gists.presentation.model.GistItemViewModel
import kotlinx.android.synthetic.main.view_gist_list.view.gistList
import kotlinx.android.synthetic.main.view_gist_list.view.gistLoader
import javax.inject.Inject

class GistListView @Inject constructor(): GistListComponentView {

    lateinit var presenter: GistsListContract.Presenter

    lateinit var view: View

    private val tag = this.javaClass.name

    override fun displayLoader() {
        view.gistList.visibility = View.VISIBLE
    }

    override fun displayError(error: Throwable) {
        Log.d(tag, "displayError: ${error.message}")
        error.printStackTrace()
    }

    override fun hideLoader() {
        view.gistLoader.visibility = View.GONE
    }

    override fun displayGists(gistsViewModel: List<GistItemViewModel>, forceNewList: Boolean) {
        if (forceNewList) {
            view.gistList.set(newItems = gistsViewModel)
        } else {
            view.gistList.add(gistsViewModel)
        }
    }

    override fun attach(root: ViewGroup) {
        view = LayoutInflater.from(root.context).inflate(R.layout.view_gist_list, root)
        init()
    }

    private fun init() {
        setupRecyclerView()
        presenter.loadGists()
    }

    private fun setupRecyclerView() {
        view.gistList.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
        view.gistList.listener = { size ->
            Log.d(tag, "loadGists($size)")
            presenter.loadGists(loadedItemsCount = size)
        }
    }

    override fun bindPresenter(presenter: GistsListContract.Presenter) {
        this.presenter = presenter
    }

    override fun onNavigationItemSelected() {
        //
    }
}