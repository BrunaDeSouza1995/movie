package com.bruna.movie.plugin.network

import com.bruna.movie.plugin.network.response.MovieDiscoverResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("discover/movie")
    fun getMovies(
        @Query("api_key") key: String = API_KEY,
        @Query("language") language: String = LANGUAGE_PT_BR,
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("page") page: Int = 1
    ): Observable<MovieDiscoverResponse>
}