package com.bruna.movie.plugin.di

import com.bruna.movie.plugin.repository.MovieRepositoryImpl
import com.bruna.movie.feature.movie.business.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindRepository(repository: MovieRepositoryImpl): MovieRepository
}
