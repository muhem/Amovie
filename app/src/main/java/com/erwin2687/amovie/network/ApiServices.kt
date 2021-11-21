package com.erwin2687.amovie.network

import com.erwin2687.amovie.model.Movie
import com.erwin2687.amovie.model.MovieResponse
import com.erwin2687.amovie.model.TvShow
import com.erwin2687.amovie.model.TvShowResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {
    @GET("discover/movie")
    fun DiscoverMovie(@Query("language") language: String): Call<MovieResponse>

    @GET("discover/tv")
    fun DiscoverTV(@Query("language") language: String): Call<TvShowResponse>

    @GET("movie/{movieid}")
    fun GetMovie(@Path("movieid") id: Int, @Query("language") language: String): Call<Movie>

    @GET("tv/{tvid}")
    fun GetTV(@Path("tvid") id: Int, @Query("language") language: String): Call<TvShow>
}