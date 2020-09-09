package com.bruna.movie.plugin.extension

fun Throwable?.orDefault(): Throwable {
    return this ?: Throwable("Generic Error")
}