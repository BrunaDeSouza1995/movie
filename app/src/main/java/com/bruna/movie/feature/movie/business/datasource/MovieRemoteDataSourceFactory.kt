package com.bruna.movie.feature.movie.business.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.bruna.movie.data.Movie
import com.bruna.movie.drivers.pading.PAGE_ONE
import com.bruna.movie.drivers.pading.PAGE_TWO
import com.bruna.movie.drivers.pading.getAdjacentPageKey
import com.bruna.movie.feature.movie.business.usecase.GetMoviesRemoteUseCase
import javax.inject.Inject
import kotlin.Result.Companion.failure
import kotlin.Result.Companion.success

class MovieRemoteDataSourceFactory @Inject constructor(
    private val useCase: GetMoviesRemoteUseCase
) : DataSource.Factory<Int, Movie>() {

    val loadedStateLiveData = MutableLiveData<Result<Boolean>>()

    override fun create(): DataSource<Int, Movie> = RemotePageKeyedDataSource()

    inner class RemotePageKeyedDataSource : PageKeyedDataSource<Int, Movie>() {
        override fun loadInitial(
            params: LoadInitialParams<Int>,
            callback: LoadInitialCallback<Int, Movie>
        ) {
            loadedStateLiveData.postValue(success(false))
            useCase(
                PAGE_ONE,
                success = {
                    if (!it.isNullOrEmpty()) callback.onResult(it, PAGE_ONE, PAGE_TWO)
                    loadedStateLiveData.postValue(success(true))
                },
                error = {
                    callback.onResult(listOf(), PAGE_ONE, PAGE_TWO)
                    if (it != null) loadedStateLiveData.postValue(failure(it))
                }
            )
        }

        override fun loadAfter(
            params: LoadParams<Int>,
            callback: LoadCallback<Int, Movie>
        ) {
            val page = params.key
            loadedStateLiveData.postValue(success(false))
            useCase(page,
                success = {
                    if (!it.isNullOrEmpty()) callback.onResult(it, params.getAdjacentPageKey())
                    loadedStateLiveData.postValue(success(true))
                },
                error = {
                    callback.onResult(listOf(), page)
                    if (it != null) loadedStateLiveData.postValue(failure(it))
                })
        }

        override fun loadBefore(
            params: LoadParams<Int>,
            callback: LoadCallback<Int, Movie>
        ) = Unit
    }
}