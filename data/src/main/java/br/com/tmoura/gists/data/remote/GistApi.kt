package br.com.tmoura.gists.data.remote

import br.com.tmoura.gists.data.remote.model.GistPayload
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GistApi {

    @GET("/gists/public")
    fun getGists(@Query("page") page: Int, @Query("perPage") perPage: Int): Single<List<GistPayload>>

    @GET("/gists/{id}")
    fun getGist(@Path("id") id: String): Single<GistPayload>

}