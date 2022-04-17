package com.mfathoer.sharecipe.data.source.remote.api

import com.mfathoer.sharecipe.BuildConfig
import com.mfathoer.sharecipe.data.source.remote.response.RecipeResponse
import com.mfathoer.sharecipe.data.source.remote.response.SearchRecipesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("/recipes/complexSearch")
    suspend fun searchRecipes(
        @Query("query") query: String,
        @Query("number") count: Int = 20,
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY
    ): SearchRecipesResponse

    @GET("/recipes/{id}/information")
    suspend fun getDetailRecipe(
        @Path("id") recipeId: Int,
        @Query("includeNutrition") includeNutrition: Boolean = false,
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY
    ): RecipeResponse
}