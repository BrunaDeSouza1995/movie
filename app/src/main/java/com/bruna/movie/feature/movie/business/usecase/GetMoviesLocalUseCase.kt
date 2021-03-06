package com.bruna.movie.feature.movie.business.usecase

import com.bruna.movie.model.Movie
import com.bruna.movie.feature.base.business.usecase.UseCase
import com.bruna.movie.feature.movie.business.repository.MovieRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetMoviesLocalUseCase @Inject constructor(
    private val repository: MovieRepository
) : UseCase<Nothing, List<Movie>>() {

    override fun execute(input: Nothing?): Observable<Result<List<Movie>>> {
        return repository.getMoviesLocal()
    }
}