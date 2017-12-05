package br.com.tmoura.gists.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.tmoura.gists.R
import br.com.tmoura.gists.presentation.contract.GistsListContract
import br.com.tmoura.gists.view.GistListComponentView
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.content
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject lateinit var gistListComponent : GistListComponentView
    @Inject lateinit var gistListPresenter: GistsListContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        gistListPresenter.register()

        setContentView(R.layout.activity_main)
        gistListComponent.attach(content)
    }

    override fun onDestroy() {
        super.onDestroy()
        gistListPresenter.unRegister()
    }

}