package br.com.tmoura.gists.gists

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import br.com.tmoura.gists.R
import br.com.tmoura.gists.presentation.contract.GistsListContract
import br.com.tmoura.gists.presentation.model.GistItemViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.gistList
import kotlinx.android.synthetic.main.activity_main.gistLoader
import javax.inject.Inject

class GistListActivity : AppCompatActivity(), GistsListContract.View {

    @Inject lateinit var presenter: GistsListContract.Presenter

    private val tag = this.javaClass.name

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()
        presenter.register()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unRegister()
    }

    override fun displayLoader() {
        gistLoader.visibility = View.VISIBLE
    }

    override fun displayError(error: Throwable) {
        Log.d(tag, "displayError: ${error.message}")
        error.printStackTrace()
    }

    override fun hideLoader() {
        gistLoader.visibility = View.GONE
    }

    override fun displayGists(gistsViewModel: List<GistItemViewModel>, forceNewList: Boolean) {
        if (forceNewList) {
            gistList.set(newItems = gistsViewModel)
        } else {
            gistList.add(gistsViewModel)
        }
    }

    private fun setupRecyclerView() {
        gistList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        gistList.listener = { size ->
            Log.d(tag, "loadGists($size)")
            presenter.loadGists(loadedItemsCount = size)
        }
    }
}