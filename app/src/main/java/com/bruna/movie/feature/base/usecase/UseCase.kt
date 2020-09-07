package com.bruna.movie.feature.base.usecase

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers.io
import kotlin.Result.Companion.failure

abstract class UseCase<I, O> {

    abstract fun execute(param: I?): Observable<Result<O>>

    operator fun invoke(
        param: I? = null,
        success: (O?) -> Unit = {},
        error: (Throwable?) -> Unit = {}
    ): Disposable {
        return execute(param)
            .onErrorReturn { failure(it) }
            .subscribeOn(io())
            .observeOn(io())
            .subscribe {
                if (it.isSuccess) success(it.getOrNull()) else error(it.exceptionOrNull())
            }
    }
}
