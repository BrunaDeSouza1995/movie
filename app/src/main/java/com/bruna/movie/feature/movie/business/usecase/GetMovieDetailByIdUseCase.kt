package com.bruna.movie.feature.movie.business.usecase

import com.bruna.movie.model.Movie
import com.bruna.movie.feature.base.usecase.UseCase
import com.bruna.movie.feature.movie.business.repository.MovieRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetMovieDetailByIdUseCase @Inject constructor(
    val repository: MovieRepository
) : UseCase<Int, Movie>() {

    override fun execute(param: Int?): Observable<Result<Movie>> {
        return param?.let { repository.getMovieDetail(it) } ?: Observable.empty()
    }
}