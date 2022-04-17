package com.mfathoer.sharecipe.domain.di

import android.content.Context
import androidx.room.Room
import com.mfathoer.sharecipe.data.source.local.AppDatabase
import com.mfathoer.sharecipe.data.source.local.BookmarkDao
import com.mfathoer.sharecipe.data.source.local.LocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {
    private const val DB_NAME = "sharecipe"

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext appContext: Context) =
        Room.databaseBuilder(appContext, AppDatabase::class.java, DB_NAME)
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideBookmarkDao(appDatabase: AppDatabase): BookmarkDao = appDatabase.bookmarkDao()

    @Singleton
    @Provides
    fun provideLocalDataSource(ioDispatcher: CoroutineDispatcher, bookmarkDao: BookmarkDao) =
        LocalDataSource(ioDispatcher, bookmarkDao)
}