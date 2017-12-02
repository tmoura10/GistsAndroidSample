package br.com.tmoura.gists.gists

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import br.com.tmoura.gists.presentation.contract.GistsListContract
import br.com.tmoura.gists.presentation.model.GistItemViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject

class GistListActivity : AppCompatActivity(), GistsListContract.View {

    @Inject lateinit var presenter: GistsListContract.Presenter

    val TAG = this.javaClass.name

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        presenter.register()
    }

    override fun onStop() {
        super.onStop()
        presenter.unRegister()
    }

    override fun displayLoader() {
        Log.d(TAG, "displayLoader")
    }

    override fun displayError(error: Throwable) {
        Log.d(TAG, "displayError: ${error.message}")
        error.printStackTrace()
    }

    override fun hideLoader() {
        Log.d(TAG, "hideLoader")
    }

    override fun displayGists(gistsViewModel: List<GistItemViewModel>) {
        Log.d(TAG, "displayGists: $gistsViewModel")
    }
}