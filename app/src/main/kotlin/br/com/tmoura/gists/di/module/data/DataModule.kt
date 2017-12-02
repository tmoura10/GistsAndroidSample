package br.com.tmoura.gists.di.module.data

import br.com.tmoura.gists.data.remote.GistApi
import br.com.tmoura.gists.di.scope.PerApplication
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
object DataModule {

    @Provides
    @PerApplication
    @JvmStatic
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient
                .Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build()
    }

    @Provides
    @PerApplication
    @JvmStatic
    fun provideCallAdapterFactory(): CallAdapter.Factory = RxJava2CallAdapterFactory.create()

    @Provides
    @PerApplication
    @JvmStatic
    fun provideConverterFactory(gson: Gson): Converter.Factory = GsonConverterFactory.create(gson)

    @Provides
    @PerApplication
    @JvmStatic
    fun provideApi(client: OkHttpClient,
                   callAdapterFactory: CallAdapter.Factory,
                   converterFactory: Converter.Factory): GistApi {
        return Retrofit
                .Builder()
                .addCallAdapterFactory(callAdapterFactory)
                .baseUrl("https://api.github.com/")
                .addConverterFactory(converterFactory)
                .client(client)
                .build()
                .create(GistApi::class.java)
    }

}