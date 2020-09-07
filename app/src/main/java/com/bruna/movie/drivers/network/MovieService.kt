package com.bruna.movie.drivers.network

import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface MovieService {

    @GET("/discover/movie")
    fun getMovies(): Observable<Nothing>
}