package com.bruna.movie.feature.movie.business.repository

import com.bruna.movie.data.Movie
import com.bruna.movie.drivers.network.response.MovieDiscoverResponse
import io.reactivex.Observable

interface MovieRepository {
    fun getMovies(): Observable<Result<MovieDiscoverResponse>>
    fun getMoviesLocal(): Observable<Result<List<Movie>>>
    fun getMoviesRemote(page: Int): Observable<Result<List<Movie>>>
}
