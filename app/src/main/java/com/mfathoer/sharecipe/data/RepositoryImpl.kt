package com.mfathoer.sharecipe.data

import com.mfathoer.sharecipe.data.source.RemoteDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource): Repository {
}