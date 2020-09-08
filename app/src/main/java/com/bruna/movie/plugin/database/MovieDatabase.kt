package com.bruna.movie.plugin.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bruna.movie.model.Movie
import com.bruna.movie.plugin.database.dao.MovieDao

@Database(entities = [Movie::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
}
