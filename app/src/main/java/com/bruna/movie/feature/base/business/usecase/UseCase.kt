package com.bruna.movie.feature.base.business.usecase

import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers.io
import kotlin.Result.Companion.failure

abstract class UseCase<I, O> {

    abstract fun execute(input: I?): Observable<Result<O>>

    operator fun invoke(
        input: I? = null,
        onDispatchResult: UseCaseResultDSL<O>.(result: Result<O>) -> Unit
    ): Disposable {
        return execute(input)
            .onErrorReturn { failure(it) }
            .subscribeOn(io())
            .observeOn(io())
            .subscribe {
                UseCaseResultDSL<O>()
                    .apply { onDispatchResult(it) }
                    .run { it.initializer(success, failure) }
            }
    }
}
