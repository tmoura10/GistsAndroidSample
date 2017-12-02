package br.com.tmoura.gists.di.subcomponent

import br.com.tmoura.gists.gists.GistListActivity
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent
interface GistListActivitySubcomponent : AndroidInjector<GistListActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<GistListActivity>()

}