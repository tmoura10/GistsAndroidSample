package br.com.tmoura.gists.data.remote

import br.com.tmoura.gists.data.remote.model.GistPayload
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface GistApi {

    @GET("/gists/public?page={page}&per_page={perPage}")
    fun getGists(@Path("page") page: Int, @Path("perPage") perPage: Int): Single<List<GistPayload>>

    @GET("/gists/{id}")
    fun getGist(@Path("id") id: String): Single<GistPayload>

}