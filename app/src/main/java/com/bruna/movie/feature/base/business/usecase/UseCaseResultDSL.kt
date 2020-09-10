package com.bruna.movie.feature.base.business.usecase

class UseCaseResultDSL<O> {

    var success: (result: O) -> Unit = {}
    var failure: (result: Throwable) -> Unit = {}

    fun <O> Result<O>.initializer(success: (result: O) -> Unit, failure: (result: Throwable) -> Unit) {
        when{
            isSuccess -> onSuccess(success)
            else -> onFailure(failure)
        }
    }

}