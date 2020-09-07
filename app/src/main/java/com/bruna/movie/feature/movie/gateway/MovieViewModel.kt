package com.bruna.movie.feature.movie.gateway

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.bruna.movie.feature.movie.usecase.GetMoviesUseCase

class MovieViewModel @ViewModelInject constructor(
    @Assisted savedStateHandle: SavedStateHandle,
    val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {

    init {
        getMoviesUseCase(
            success = {
                Log.d("Bruna", "Success: ${it.toString()}")
            },
            error = {
                Log.d("Bruna", "Error: ${it?.message}")
            }
        )
    }

    val teste = MutableLiveData<String>()

    fun teste() {
        teste.value = "Bruna"
    }
}