package com.bruna.movie.plugin.pading

import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList

const val PAGE_ZERO = 0
const val PAGE_ONE = 1
const val PAGE_TWO = 2
private const val LIST_SIZE = 100

fun PageKeyedDataSource.LoadParams<Int>.getAdjacentPageKey(): Int = key + 1

val pagedListConfig = PagedList.Config
    .Builder()
    .setEnablePlaceholders(false)
    .setInitialLoadSizeHint(LIST_SIZE)
    .setPageSize(LIST_SIZE)
    .build()
