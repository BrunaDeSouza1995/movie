package com.bruna.movie.feature.movie.business.usecase

import com.bruna.movie.extension.asSuccess
import com.bruna.movie.extension.assertEquals
import com.bruna.movie.feature.base.business.usecase.assertFailure
import com.bruna.movie.feature.base.business.usecase.assertSuccess
import com.bruna.movie.feature.movie.business.repository.MovieRepository
import com.bruna.movie.mock.movieMock
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations.initMocks

class GetMoviesRemoteUseCaseTest {

    @Mock
    lateinit var mockRepository: MovieRepository

    lateinit var useCase: GetMoviesRemoteUseCase

    private val page = 1

    @Before
    fun setUp() {
        initMocks(this)
        useCase = GetMoviesRemoteUseCase(mockRepository)
    }

    @Test
    fun `when execute use case then return success result`() {
        `when`(mockRepository.getMoviesRemote(page)).thenReturn(listOf(movieMock).asSuccess())

        useCase.assertSuccess(page) {
            it.first().assertEquals(movieMock)
        }
    }

    @Test
    fun `when execute use case and throw exception then return error result`() {
        `when`(mockRepository.getMoviesRemote(page)).thenThrow(RuntimeException())

        useCase.assertFailure(page) {
            assertTrue(it is RuntimeException)
        }
    }

    @Test
    fun `when execute use case and do not pass id then return error result`() {
        useCase.assertFailure {
            assertTrue(it is NullPointerException)
        }
    }
}