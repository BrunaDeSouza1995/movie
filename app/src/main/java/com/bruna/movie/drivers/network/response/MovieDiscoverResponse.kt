package com.bruna.movie.drivers.network.response

data class MovieDiscoverResponse(
    val page: Int,
    val results: List<MovieDiscoverItemResponse>,
    val total_pages: Int,
    val total_results: Int
)