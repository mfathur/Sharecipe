package com.mfathoer.sharecipe.data

import com.mfathoer.sharecipe.data.source.remote.RemoteDataSource
import javax.inject.Inject


class RepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource): Repository {
}