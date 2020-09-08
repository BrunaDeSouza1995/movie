package com.bruna.movie.plugin.di

import android.content.Context
import androidx.room.Room.databaseBuilder
import com.bruna.movie.plugin.database.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

private const val NAME_DATABASE = "movie_database"

@Module
@InstallIn(ApplicationComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun getInstanceDatabase(@ApplicationContext context: Context): MovieDatabase {
        return databaseBuilder(context, MovieDatabase::class.java, NAME_DATABASE).build()
    }

    @Singleton
    @Provides
    fun provideMovieDao(database: MovieDatabase) = database.movieDao()
}