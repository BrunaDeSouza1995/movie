package com.bruna.movie.plugin.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bruna.movie.model.Movie
import io.reactivex.Observable
import org.jetbrains.annotations.TestOnly

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movies: List<Movie>)

    @Query("SELECT * FROM movie")
    fun getMoviesObservable(): Observable<List<Movie>>

    @TestOnly
    @Query("SELECT * FROM movie")
    fun getMovies(): List<Movie>

    @Query("SELECT * FROM movie WHERE id =:id")
    fun getMovieById(id: Int): Observable<Movie>
}