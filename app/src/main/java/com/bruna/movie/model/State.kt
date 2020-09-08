package com.bruna.movie.model

private const val ERROR_GENERIC = "Erro inesperado"

sealed class State
object LoadingState : State()
object LoadedState : State()
data class FailedState(val message: String? = ERROR_GENERIC) : State()