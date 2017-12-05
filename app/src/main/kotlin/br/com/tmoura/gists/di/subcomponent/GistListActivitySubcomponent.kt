package br.com.tmoura.gists.di.subcomponent

import br.com.tmoura.gists.activity.MainActivity
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent
interface GistListActivitySubcomponent : AndroidInjector<MainActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<MainActivity>()

}