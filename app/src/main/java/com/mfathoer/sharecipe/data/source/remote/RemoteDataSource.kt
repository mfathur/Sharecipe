package com.mfathoer.sharecipe.data.source.remote

import com.mfathoer.sharecipe.data.source.remote.api.ApiService
import javax.inject.Inject
import javax.inject.Singleton

class RemoteDataSource @Inject constructor(private val apiService: ApiService) {
}