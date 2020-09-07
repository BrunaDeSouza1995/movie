package com.bruna.movie.drivers.repository

import com.bruna.movie.drivers.network.MovieService
import com.bruna.movie.drivers.network.response.MovieDiscoverResponse
import com.bruna.movie.feature.movie.usecase.MovieRepository
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject
import kotlin.Result.Companion.failure
import kotlin.Result.Companion.success

class MovieRepositoryImpl @Inject constructor(
    private val service: MovieService
) : MovieRepository {

    override fun getMovies(): Observable<Result<MovieDiscoverResponse>> {
        return service.getMovies()
            .map { success(it) }
            .onErrorReturn { failure(it) }
    }
}
