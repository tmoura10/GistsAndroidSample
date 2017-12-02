package br.com.tmoura.gists.di.module.domain

import br.com.tmoura.gists.data.repository.GistRepositoryImpl
import br.com.tmoura.gists.di.scope.PerApplication
import br.com.tmoura.gists.domain.repository.GistRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    @PerApplication
    abstract fun bindGistRepository(impl: GistRepositoryImpl): GistRepository
}