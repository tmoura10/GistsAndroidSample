package br.com.tmoura.gists.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import br.com.tmoura.gists.R
import br.com.tmoura.gists.presentation.contract.GistsListContract
import br.com.tmoura.gists.view.GistListViewComponent
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.bottomNavigation
import kotlinx.android.synthetic.main.activity_main.content
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject lateinit var gistListComponent : GistListViewComponent
    @Inject lateinit var gistListPresenter: GistsListContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        gistListPresenter.register()

        setContentView(R.layout.activity_main)
        gistListComponent.attach(content)

        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        bottomNavigation.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_home -> gistListComponent.onNavigationItemSelected()
                else -> showMessage(menuItem.title.toString())
            }
            true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        gistListPresenter.unRegister()
    }

    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}