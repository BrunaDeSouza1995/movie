package com.bruna.movie.feature.base.business.usecase

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers.mainThread
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers.io
import kotlin.Result.Companion.failure

abstract class UseCase<I, O> {

    abstract fun execute(input: I?): Observable<Result<O>>

    operator fun invoke(
        input: I? = null,
        subscribeOn: Scheduler = io(),
        observeOn: Scheduler = mainThread(),
        onDispatchResult: UseCaseResultDSL<O>.(result: Result<O>) -> Unit
    ): Disposable {
        return Observable
            .fromCallable { input ?: Observable.empty<I>() }
            .concatMap { execute(input) }
            .onErrorReturn { failure(it) }
            .subscribeOn(subscribeOn)
            .observeOn(observeOn)
            .subscribe {
                UseCaseResultDSL<O>()
                    .apply { onDispatchResult(it) }
                    .run { it.initializer(success, failure) }
            }
    }
}
