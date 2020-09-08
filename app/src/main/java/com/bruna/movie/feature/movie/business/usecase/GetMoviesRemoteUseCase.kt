package com.bruna.movie.feature.movie.business.usecase

import com.bruna.movie.data.Movie
import com.bruna.movie.feature.base.usecase.UseCase
import com.bruna.movie.feature.movie.business.repository.MovieRepository
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class GetMoviesRemoteUseCase @Inject constructor(
    private val repository: MovieRepository
) : UseCase<Int, List<Movie>>() {

    override fun execute(param: Int?): Observable<Result<List<Movie>>> {
        return param?.let(repository::getMoviesRemote) ?: Observable.empty()
    }
}