package br.com.tmoura.gists.di.module

import android.app.Application
import android.content.Context
import br.com.tmoura.gists.di.scope.PerApplication
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides

@Module
object ApplicationModule {

    @Provides
    @PerApplication
    @JvmStatic
    fun provideContext(application: Application): Context = application

    @Provides
    @PerApplication
    @JvmStatic
    fun providesGson() = GsonBuilder().setLenient().create()

}