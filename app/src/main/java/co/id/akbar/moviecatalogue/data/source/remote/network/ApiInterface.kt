package co.id.akbar.moviecatalogue.data.source.remote.network

import co.id.akbar.moviecatalogue.BuildConfig.API_KEY
import co.id.akbar.moviecatalogue.data.source.local.entity.MovieEntity
import co.id.akbar.moviecatalogue.data.source.local.entity.TvShow
import co.id.akbar.moviecatalogue.data.source.remote.network.response.MovieResponse
import co.id.akbar.moviecatalogue.data.source.remote.network.response.TvResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("movie/popular?api_key=$API_KEY")
    fun movies(@Query("page") page: Int): Call<MovieResponse>

    @GET("movie/{id}?api_key=$API_KEY")
    fun movie(@Path("id") id: Int): Call<MovieEntity>

    @GET("tv/popular?api_key=$API_KEY")
    fun tvShows(@Query("page") page: Int): Call<TvResponse>

    @GET("tv/{id}?api_key=$API_KEY")
    fun tvShow(@Path("id") id: Int): Call<TvShow>
}