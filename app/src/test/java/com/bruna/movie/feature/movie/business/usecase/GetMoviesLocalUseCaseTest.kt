package com.bruna.movie.feature.movie.business.usecase

import com.bruna.movie.extension.asSuccess
import com.bruna.movie.extension.assertEquals
import com.bruna.movie.feature.base.business.usecase.assertFailure
import com.bruna.movie.feature.base.business.usecase.assertSuccess
import com.bruna.movie.feature.movie.business.repository.MovieRepository
import com.bruna.movie.model.Movie.Companion.mock
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations.initMocks
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetMoviesLocalUseCaseTest {

    @Mock
    lateinit var mockRepository: MovieRepository

    private lateinit var useCase: GetMoviesLocalUseCase

    @Before
    fun setUp() {
        initMocks(this)
        useCase = GetMoviesLocalUseCase(mockRepository)
    }

    @Test
    fun `when execute use case then return success result`() {
        `when`(mockRepository.getMoviesLocal()).thenReturn(listOf(mock).asSuccess())

        useCase.assertSuccess {
            it.first().assertEquals(mock)
        }
    }

    @Test
    fun `when execute use case and throw exception then return error result`() {
        `when`(mockRepository.getMoviesLocal()).thenThrow(RuntimeException())

        useCase.assertFailure {
            assertTrue(it is RuntimeException)
        }
    }
}