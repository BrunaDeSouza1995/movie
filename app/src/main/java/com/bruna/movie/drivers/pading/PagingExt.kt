package com.bruna.movie.drivers.pading

import androidx.paging.PageKeyedDataSource

const val PAGE_ZERO = 0
const val PAGE_ONE = 1
const val PAGE_TWO = 2

fun PageKeyedDataSource.LoadParams<Int>.getAdjacentPageKey(): Int = key + 1