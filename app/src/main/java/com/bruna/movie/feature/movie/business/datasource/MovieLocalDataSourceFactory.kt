package com.bruna.movie.feature.movie.business.datasource

import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.bruna.movie.data.Movie
import com.bruna.movie.drivers.pading.PAGE_ONE
import com.bruna.movie.drivers.pading.PAGE_ZERO
import com.bruna.movie.feature.movie.business.usecase.GetMoviesLocalUseCase
import javax.inject.Inject

class MovieLocalDataSourceFactory @Inject constructor(
    val useCase: GetMoviesLocalUseCase
) : DataSource.Factory<Int, Movie>() {

    override fun create(): DataSource<Int, Movie> = LocalPageKeyedDataSource()

    inner class LocalPageKeyedDataSource : PageKeyedDataSource<Int, Movie>() {

        override fun loadInitial(
            params: LoadInitialParams<Int>,
            callback: LoadInitialCallback<Int, Movie>
        ) {
            useCase(
                success = { if (!it.isNullOrEmpty()) callback.onResult(it, PAGE_ZERO, PAGE_ONE) }
            )
        }

        override fun loadAfter(
            params: LoadParams<Int>,
            callback: LoadCallback<Int, Movie>
        ) = Unit

        override fun loadBefore(
            params: LoadParams<Int>,
            callback: LoadCallback<Int, Movie>
        ) = Unit
    }
}