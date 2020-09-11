package com.bruna.movie.extension

import io.reactivex.Observable
import kotlin.Result.Companion.success

fun <T> T.toObservable(): Observable<T> {
    return Observable.fromCallable { this }
}

fun <T> T.asSuccess(): Observable<Result<T>> {
    return toObservable().map { success(it) }
}