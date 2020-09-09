package com.bruna.movie.feature.movie.gateway

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.bruna.movie.model.Movie
import com.bruna.movie.plugin.extension.asLiveData
import com.bruna.movie.feature.movie.business.datasource.MovieLocalDataSourceFactory
import com.bruna.movie.feature.movie.business.datasource.MovieRemoteDataSourceFactory
import com.bruna.movie.feature.movie.business.usecase.GetMovieDetailByIdUseCase

private const val LIST_SIZE = 20

class MovieViewModel @ViewModelInject constructor(
    @Assisted savedStateHandle: SavedStateHandle,
    remoteDataSourceFactory: MovieRemoteDataSourceFactory,
    private val localDataSourceFactory: MovieLocalDataSourceFactory,
    private val getMovieDetailByIdUseCase: GetMovieDetailByIdUseCase
) : ViewModel() {

    private val moviesMediatorLiveData = MediatorLiveData<PagedList<Movie>>()
    private val movieMutableLiveData = MutableLiveData<Movie?>()

    val loadedLiveData = remoteDataSourceFactory.loadedStateLiveData.asLiveData()
    val moviesLiveData = moviesMediatorLiveData.asLiveData()
    val movieLiveData = movieMutableLiveData.asLiveData()

    init {
        val remoteLivePagedList =
            LivePagedListBuilder(remoteDataSourceFactory, getPagedListConfig())
                .setBoundaryCallback(BoundaryCallback())
                .build()

        moviesMediatorLiveData.addSource(remoteLivePagedList) { moviesMediatorLiveData.value = it }
    }

    private fun getPagedListConfig(): PagedList.Config {
        return PagedList.Config
            .Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(LIST_SIZE)
            .setPageSize(LIST_SIZE)
            .build()
    }

    fun navigateToMovieDetail(id: Int) {
        getMovieDetailByIdUseCase(param = id, onSuccess = movieMutableLiveData::postValue)
    }

    inner class BoundaryCallback : PagedList.BoundaryCallback<Movie>() {
        private val databaseLivePagedList =
            LivePagedListBuilder(localDataSourceFactory, getPagedListConfig()).build()

        override fun onZeroItemsLoaded() {
            super.onZeroItemsLoaded()
            moviesMediatorLiveData.addSource(databaseLivePagedList) {
                moviesMediatorLiveData.value = it
                moviesMediatorLiveData.removeSource(databaseLivePagedList)
            }
        }
    }
}