package br.com.tmoura.gists.di.module.data

import br.com.tmoura.gists.data.dataset.GistLocalDataSet
import br.com.tmoura.gists.data.dataset.GistRemoteDataSet
import br.com.tmoura.gists.data.local.GistLocalDataSetImpl
import br.com.tmoura.gists.data.remote.GistRemoteDataSetImpl
import br.com.tmoura.gists.di.scope.PerApplication
import dagger.Binds
import dagger.Module

@Module
abstract class DataSetModule {

    @Binds
    @PerApplication
    abstract fun bindGistRemoteDataSet(impl: GistRemoteDataSetImpl): GistRemoteDataSet

    @Binds
    @PerApplication
    abstract fun bindGistLocalDataSet(impl: GistLocalDataSetImpl): GistLocalDataSet

}