package com.bruna.movie.plugin.repository

import com.bruna.movie.model.Movie
import com.bruna.movie.plugin.database.dao.MovieDao
import com.bruna.movie.plugin.network.MovieService
import com.bruna.movie.plugin.network.response.MovieDiscoverResponse
import com.bruna.movie.feature.movie.business.repository.MovieRepository
import io.reactivex.Observable
import javax.inject.Inject
import kotlin.Result.Companion.success

class MovieRepositoryImpl @Inject constructor(
    private val service: MovieService,
    private val dao: MovieDao
) : MovieRepository {

    override fun getMovies(): Observable<Result<MovieDiscoverResponse>> {
        return service.getMovies()
            .map { success(it) }
    }

    override fun getMoviesLocal(): Observable<Result<List<Movie>>> {
        return dao.getMoviesObservable()
            .map { success(it) }
    }

    override fun getMoviesRemote(page: Int): Observable<Result<List<Movie>>> {
        return service.getMovies(page = page)
            .flatMapIterable { it.results }
            .map(Movie.Companion::converterByMovieDiscoverResponse)
            .toList()
            .toObservable()
            .doOnNext(dao::insert)
            .map { success(it) }
    }

    override fun getMovieDetail(id: Int): Observable<Result<Movie>> {
        return dao.getMovieById(id)
            .map { success(it) }
    }
}
