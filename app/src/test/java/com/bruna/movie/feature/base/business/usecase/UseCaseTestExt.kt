package com.bruna.movie.feature.base.business.usecase

import io.reactivex.exceptions.OnErrorNotImplementedException
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers.trampoline
import org.junit.Assert.fail

private fun <I, O> UseCase<I, O>.invokeUseCase(
    param: I? = null,
    assertions: UseCaseResultDSL<O>.(Result<O>) -> Unit = {}
) {
    val failures = arrayListOf<Throwable>()
    RxJavaPlugins.setErrorHandler { failures.add(it) }

    invoke(param, trampoline(), trampoline(), assertions)

    failures.forEach { failure ->
        when (failure) {
            is OnErrorNotImplementedException -> throw failure.cause ?: failure
            else -> throw failure
        }
    }
}

fun <I, O> UseCase<I, O>.assertSuccess(
    param: I? = null,
    assertion: (O) -> Unit = {}
) {
    invokeUseCase(param) {
        success = assertion
        failure = { fail("Expected success but was error") }
    }
}

fun <I, O> UseCase<I, O>.assertFailure(
    param: I? = null,
    assertion: (Throwable) -> Unit = {}
) {
    invokeUseCase(param) {
        success = { fail("Expected error but was success") }
        failure = assertion
    }
}


