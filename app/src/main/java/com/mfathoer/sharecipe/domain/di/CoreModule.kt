package com.mfathoer.sharecipe.domain.di

import com.mfathoer.sharecipe.data.Repository
import com.mfathoer.sharecipe.data.RepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CoreModule {
    @Singleton
    @Binds
    abstract fun provideRepository(repositoryImpl: RepositoryImpl) : Repository
}