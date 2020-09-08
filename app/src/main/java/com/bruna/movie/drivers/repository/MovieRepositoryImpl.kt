package com.bruna.movie.drivers.repository

import com.bruna.movie.data.Movie
import com.bruna.movie.drivers.database.dao.MovieDao
import com.bruna.movie.drivers.network.MovieService
import com.bruna.movie.drivers.network.response.MovieDiscoverResponse
import com.bruna.movie.feature.movie.business.repository.MovieRepository
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject
import kotlin.Result.Companion.failure
import kotlin.Result.Companion.success

class MovieRepositoryImpl @Inject constructor(
    private val service: MovieService,
    private val dao: MovieDao
) : MovieRepository {

    override fun getMovies(): Observable<Result<MovieDiscoverResponse>> {
        return service.getMovies()
            .map { success(it) }
            .onErrorReturn { failure(it) }
    }

    override fun getMoviesLocal(): Observable<Result<List<Movie>>> {
        return dao.getMovies()
            .map { success(it) }
            .onErrorReturn { error(it) }
    }

    override fun getMoviesRemote(page: Int): Observable<Result<List<Movie>>> {
        return service.getMovies(page = page)
            .flatMapIterable { it.results }
            .map(Movie.Companion::converterByMovieDiscoverResponse)
            .toList()
            .toObservable()
            .doOnNext(dao::insert)
            .map { success(it) }
            .onErrorReturn { error(it) }
    }
}
