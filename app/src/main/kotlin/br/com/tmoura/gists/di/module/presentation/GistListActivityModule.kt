package br.com.tmoura.gists.di.module.presentation

import br.com.tmoura.gists.di.scope.PerActivity
import br.com.tmoura.gists.view.GistListView
import br.com.tmoura.gists.presentation.contract.GistsListContract
import br.com.tmoura.gists.presentation.presenter.GistListPresenterImpl
import br.com.tmoura.gists.view.GistListViewComponent
import dagger.Binds
import dagger.Module

@Module
abstract class GistListActivityModule {

    @Binds
    @PerActivity
    abstract fun bindPresenter(impl: GistListPresenterImpl): GistsListContract.Presenter

    @Binds
    @PerActivity
    abstract fun bindComponent(impl: GistListView): GistListViewComponent

    @Binds
    @PerActivity
    abstract fun bindView(impl: GistListViewComponent): GistsListContract.View

}