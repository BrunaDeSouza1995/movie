package com.bruna.movie.feature.movie.business.usecase

import com.bruna.movie.model.Movie
import com.bruna.movie.feature.base.business.usecase.UseCase
import com.bruna.movie.feature.movie.business.repository.MovieRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetMoviesRemoteUseCase @Inject constructor(
    private val repository: MovieRepository
) : UseCase<Int, List<Movie>>() {

    override fun execute(input: Int?): Observable<Result<List<Movie>>> {
        return input?.let(repository::getMoviesRemote) ?: Observable.empty()
    }
}