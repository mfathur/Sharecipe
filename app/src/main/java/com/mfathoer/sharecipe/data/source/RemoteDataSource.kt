package com.mfathoer.sharecipe.data.source

import com.mfathoer.sharecipe.data.source.api.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {
}