package com.mfathoer.sharecipe.data.source.remote

import com.mfathoer.sharecipe.data.source.remote.api.ApiService
import com.mfathoer.sharecipe.data.source.remote.response.RecipeResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.Default,
    private val apiService: ApiService
) {

    suspend fun searchRecipes(query: String): List<RecipeResponse?> {
        return withContext(ioDispatcher) {
            val response = apiService.searchRecipes(query)
            return@withContext response.results ?: emptyList()
        }
    }

    suspend fun getDetailRecipe(recipeId: Int): RecipeResponse {
        return withContext(ioDispatcher) {
            return@withContext apiService.getDetailRecipe(recipeId)
        }
    }
}