package com.bruna.movie.feature.movie.usecase

import com.bruna.movie.drivers.network.response.MovieDiscoverResponse
import io.reactivex.rxjava3.core.Observable

interface MovieRepository {
    fun getMovies(): Observable<Result<MovieDiscoverResponse>>
}
