package com.bruna.movie.drivers.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bruna.movie.data.Movie
import io.reactivex.rxjava3.core.Observable

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie")
    fun getMovies(): Observable<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movies:List<Movie>)
}