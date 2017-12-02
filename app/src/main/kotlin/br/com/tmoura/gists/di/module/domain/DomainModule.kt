package br.com.tmoura.gists.di.module.domain

import br.com.tmoura.gists.di.qualifier.IoScheduler
import br.com.tmoura.gists.di.qualifier.MainScheduler
import br.com.tmoura.gists.di.scope.PerApplication
import br.com.tmoura.gists.domain.SchedulersProvider
import br.com.tmoura.gists.domain.SchedulersProviderImpl
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@Module
object DomainModule {

    @Provides
    @PerApplication
    @JvmStatic
    @IoScheduler
    fun provideIoScheduler(): Scheduler = Schedulers.io()

    @Provides
    @PerApplication
    @JvmStatic
    @MainScheduler
    fun provideMainScheduler(): Scheduler = AndroidSchedulers.mainThread()

    @Provides
    @PerApplication
    @JvmStatic
    fun provideSchedulersProvider(
            @IoScheduler ioScheduler: Scheduler,
            @MainScheduler mainScheduler: Scheduler
    ): SchedulersProvider = SchedulersProviderImpl(
            schedulerToSubscribe = ioScheduler,
            schedulerToObserve = mainScheduler
    )

}