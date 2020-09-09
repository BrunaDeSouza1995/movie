package com.bruna.movie.feature.movie.business.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.bruna.movie.model.*
import com.bruna.movie.plugin.pading.PAGE_ONE
import com.bruna.movie.plugin.pading.PAGE_TWO
import com.bruna.movie.plugin.pading.getAdjacentPageKey
import com.bruna.movie.feature.movie.business.usecase.GetMoviesRemoteUseCase
import javax.inject.Inject

class MovieRemoteDataSourceFactory @Inject constructor(
    private val useCase: GetMoviesRemoteUseCase
) : DataSource.Factory<Int, Movie>() {

    val loadedStateLiveData = MutableLiveData<State>()

    override fun create(): DataSource<Int, Movie> = RemotePageKeyedDataSource()

    inner class RemotePageKeyedDataSource : PageKeyedDataSource<Int, Movie>() {

        override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Movie>) {
            loadedStateLiveData.postValue(LoadingState)
            useCase(
                PAGE_ONE,
                onSuccess = { handleUseCaseLoadInitialSuccess(it.orEmpty(), callback) },
                onError = { handleUseCaseLoadInitialError(it, callback) }
            )
        }

        private fun handleUseCaseLoadInitialSuccess(list: List<Movie>, callback: LoadInitialCallback<Int, Movie>) {
            if(list.isNotEmpty()) callback.onResult(list, PAGE_ONE, PAGE_TWO)
            loadedStateLiveData.postValue(LoadedState)
        }

        private fun handleUseCaseLoadInitialError(throwable: Throwable, callback: LoadInitialCallback<Int, Movie>) {
            callback.onResult(listOf(), PAGE_ONE, PAGE_TWO)
            loadedStateLiveData.postValue(FailedState(throwable.message))
        }

        override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
            val page = params.key
            loadedStateLiveData.postValue(LoadingState)
            useCase(
                page,
                onSuccess = { handleUseCaseLoadAfterSuccess(it.orEmpty(), callback, params) },
                onError = { handleUseCaseLoadAfterError(callback, params.key, it) })
        }

        private fun handleUseCaseLoadAfterSuccess(list: List<Movie>, callback: LoadCallback<Int, Movie>, params: LoadParams<Int>) {
            if(list.isNotEmpty())  callback.onResult(list, params.getAdjacentPageKey())
            loadedStateLiveData.postValue(LoadedState)
        }

        private fun handleUseCaseLoadAfterError(callback: LoadCallback<Int, Movie>, page: Int, throwable: Throwable) {
            callback.onResult(listOf(), page)
            loadedStateLiveData.postValue(FailedState(throwable.message))
        }

        override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) = Unit
    }
}