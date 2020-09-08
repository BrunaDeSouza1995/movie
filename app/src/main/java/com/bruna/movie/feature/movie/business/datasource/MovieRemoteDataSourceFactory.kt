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
        override fun loadInitial(
            params: LoadInitialParams<Int>,
            callback: LoadInitialCallback<Int, Movie>
        ) {
            loadedStateLiveData.postValue(LoadingState)
            useCase(
                PAGE_ONE,
                success = {
                    if (!it.isNullOrEmpty()) callback.onResult(it, PAGE_ONE, PAGE_TWO)
                    loadedStateLiveData.postValue(LoadedState)
                },
                error = {
                    callback.onResult(listOf(), PAGE_ONE, PAGE_TWO)
                    if (it != null) loadedStateLiveData.postValue(FailedState(it.message))
                }
            )
        }

        override fun loadAfter(
            params: LoadParams<Int>,
            callback: LoadCallback<Int, Movie>
        ) {
            val page = params.key
            loadedStateLiveData.postValue(LoadingState)
            useCase(page,
                success = {
                    if (!it.isNullOrEmpty()) callback.onResult(it, params.getAdjacentPageKey())
                    loadedStateLiveData.postValue(LoadedState)

                },
                error = {
                    callback.onResult(listOf(), page)
                    if (it != null) loadedStateLiveData.postValue(FailedState(it.message))
                })
        }

        override fun loadBefore(
            params: LoadParams<Int>,
            callback: LoadCallback<Int, Movie>
        ) = Unit
    }
}