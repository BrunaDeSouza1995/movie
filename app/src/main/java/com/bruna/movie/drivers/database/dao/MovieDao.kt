package com.bruna.movie.drivers.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bruna.movie.data.Movie
import io.reactivex.Observable

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movies: List<Movie>)

    @Query("SELECT * FROM movie")
    fun getMovies(): Observable<List<Movie>>

    @Query("SELECT * FROM movie WHERE id =:id")
    fun getMovieById(id: Int): Observable<Movie>
}