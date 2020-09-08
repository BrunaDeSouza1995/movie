package com.bruna.movie.drivers.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bruna.movie.data.Movie
import com.bruna.movie.drivers.database.dao.MovieDao

@Database(entities = [Movie::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
}
