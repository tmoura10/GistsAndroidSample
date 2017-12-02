package br.com.tmoura.gists.di.module.domain

import br.com.tmoura.gists.di.scope.PerActivity
import br.com.tmoura.gists.domain.interactor.GetGistInteractor
import br.com.tmoura.gists.domain.interactor.GetGistsInteractor
import br.com.tmoura.gists.domain.interactor.GetStarredGistsInteractor
import br.com.tmoura.gists.domain.interactor.StarGistInteractor
import br.com.tmoura.gists.domain.interactor.impl.GetGistInteractorImpl
import br.com.tmoura.gists.domain.interactor.impl.GetGistsInteractorImpl
import br.com.tmoura.gists.domain.interactor.impl.GetStarredGistsInteractorImpl
import br.com.tmoura.gists.domain.interactor.impl.StarGistInteractorImpl
import dagger.Binds
import dagger.Module

@Module
abstract class InteractorsModule {

    @Binds
    @PerActivity
    abstract fun bindGetGistInteractor(impl: GetGistInteractorImpl): GetGistInteractor

    @Binds
    @PerActivity
    abstract fun bindGetGistsInteractor(impl: GetGistsInteractorImpl): GetGistsInteractor

    @Binds
    @PerActivity
    abstract fun bindGetStarredGistsInteractor(impl: GetStarredGistsInteractorImpl): GetStarredGistsInteractor

    @Binds
    @PerActivity
    abstract fun bindStarGistInteractor(impl: StarGistInteractorImpl): StarGistInteractor

}