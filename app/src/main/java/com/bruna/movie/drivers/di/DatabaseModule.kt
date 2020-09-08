package com.bruna.movie.drivers.di

import android.content.Context
import androidx.room.Room
import com.bruna.movie.drivers.database.MovieDatabase
import com.bruna.movie.drivers.database.dao.MovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

private const val NAME_DATABASE = "movie_database"

@Module
@InstallIn(ApplicationComponent::class)
class DatabaseModule {

    @Volatile
    private var INSTANCE: MovieDatabase? = null

    @Provides
    fun getInstanceDatabase(context: Context): MovieDatabase {
        return INSTANCE ?: synchronized(this) {
            Room.databaseBuilder(context, MovieDatabase::class.java, NAME_DATABASE)
                .build()
                .also { INSTANCE = it }
        }
    }

    @Provides
    fun provideMovieDao(database: MovieDatabase): MovieDao? {
        return database.movieDao()
    }
}