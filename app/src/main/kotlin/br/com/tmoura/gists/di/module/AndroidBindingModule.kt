package br.com.tmoura.gists.di.module

import br.com.tmoura.gists.di.module.presentation.GistListActivityModule
import br.com.tmoura.gists.di.scope.PerActivity
import br.com.tmoura.gists.gists.GistListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AndroidBindingModule {

    @PerActivity
    @ContributesAndroidInjector(modules = arrayOf(GistListActivityModule::class))
    abstract fun bindGistListActivity(): GistListActivity

}