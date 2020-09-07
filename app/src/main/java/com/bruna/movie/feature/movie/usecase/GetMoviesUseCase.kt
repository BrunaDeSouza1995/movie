package com.bruna.movie.feature.movie.usecase

import com.bruna.movie.drivers.network.response.MovieDiscoverResponse
import com.bruna.movie.feature.base.usecase.UseCase
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) : UseCase<Nothing, MovieDiscoverResponse>() {

    override fun execute(param: Nothing?): Observable<Result<MovieDiscoverResponse>> {
        return repository.getMovies()
    }
}