package br.com.tmoura.gists.di

import br.com.tmoura.gists.GistsApp
import br.com.tmoura.gists.di.module.AndroidBindingModule
import br.com.tmoura.gists.di.module.ApplicationModule
import br.com.tmoura.gists.di.module.data.DataModule
import br.com.tmoura.gists.di.module.data.DataSetModule
import br.com.tmoura.gists.di.module.domain.DomainModule
import br.com.tmoura.gists.di.module.domain.InteractorsModule
import br.com.tmoura.gists.di.module.domain.RepositoryModule
import br.com.tmoura.gists.di.scope.PerApplication
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@PerApplication
@Component(modules = arrayOf(
        AndroidSupportInjectionModule::class,
        ApplicationModule::class,
        AndroidBindingModule::class,
        DataModule::class,
        DomainModule::class,
        InteractorsModule::class,
        RepositoryModule::class,
        DataSetModule::class
))
interface ApplicationComponent : AndroidInjector<GistsApp> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<GistsApp>()

}