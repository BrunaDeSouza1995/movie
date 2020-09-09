package com.bruna.movie.feature.movie.business.datasource

import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.bruna.movie.model.Movie
import com.bruna.movie.plugin.pading.PAGE_ONE
import com.bruna.movie.plugin.pading.PAGE_ZERO
import com.bruna.movie.feature.movie.business.usecase.GetMoviesLocalUseCase
import com.bruna.movie.model.LoadedState
import com.bruna.movie.plugin.pading.PAGE_TWO
import com.bruna.movie.plugin.pading.getAdjacentPageKey
import javax.inject.Inject

class MovieLocalDataSourceFactory @Inject constructor(
    val useCase: GetMoviesLocalUseCase
) : DataSource.Factory<Int, Movie>() {

    override fun create(): DataSource<Int, Movie> = LocalPageKeyedDataSource()

    inner class LocalPageKeyedDataSource : PageKeyedDataSource<Int, Movie>() {

        override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Movie>) {
            useCase(onSuccess = { handleUseCaseLoadInitialSuccess(it.orEmpty(), callback) })
        }

        private fun handleUseCaseLoadInitialSuccess(list: List<Movie>, callback: LoadInitialCallback<Int, Movie>) {
            if (list.isNotEmpty()) callback.onResult(list, PAGE_ONE, PAGE_TWO)
        }

        override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) = Unit

        override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) = Unit
    }
}