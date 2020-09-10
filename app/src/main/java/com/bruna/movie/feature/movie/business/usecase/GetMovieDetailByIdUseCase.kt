package com.bruna.movie.feature.movie.business.usecase

import com.bruna.movie.feature.base.business.usecase.UseCase
import com.bruna.movie.feature.movie.business.repository.MovieRepository
import com.bruna.movie.model.Movie
import io.reactivex.Observable
import javax.inject.Inject

class GetMovieDetailByIdUseCase @Inject constructor(
    val repository: MovieRepository
) : UseCase<Int, Movie>() {

    override fun execute(input: Int?): Observable<Result<Movie>> {
        return repository.getMovieDetail(input!!)
    }
}