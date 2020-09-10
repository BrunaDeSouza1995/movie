package com.bruna.movie.plugin.database.dao

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.bruna.movie.model.Movie
import com.bruna.movie.plugin.database.MovieDatabaseTestRule
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MovieDaoTest {
    @get:Rule
    val riderDatabaseRule = MovieDatabaseTestRule()

    private var movieDao: MovieDao? = null

    @Before
    fun setUp() {
        movieDao = riderDatabaseRule.database?.movieDao()
    }

    @Test
    fun whenInsertItemThenGetItemMovie() {
        val movies = listOf(Movie.mock)
        movieDao?.insert(movies)
        assertEquals(movieDao?.getMovies(), movies)
    }
}