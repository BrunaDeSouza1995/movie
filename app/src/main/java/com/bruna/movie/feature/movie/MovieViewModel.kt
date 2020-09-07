package com.bruna.movie.feature.movie

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class MovieViewModel @ViewModelInject constructor(
    @Assisted savedStateHandle: SavedStateHandle
) : ViewModel() {

    val teste = MutableLiveData<String>()

    fun teste() {
        teste.value = "Bruna"
    }
}