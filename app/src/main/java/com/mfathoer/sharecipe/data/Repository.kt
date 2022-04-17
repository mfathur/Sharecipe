package com.mfathoer.sharecipe.data

import com.mfathoer.sharecipe.domain.model.Recipe
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun searchRecipes(query: String): List<Recipe>
    suspend fun getDetailRecipe(recipeId: Int): Recipe

    suspend fun getAllBookmarkedRecipes(): Flow<List<Recipe>>
    suspend fun getBookmarkedRecipeById(recipeId: Int): Recipe
    suspend fun deleteBookmarkedRecipe(recipe: Recipe)
    suspend fun insertBookmarkedRecipe(recipe: Recipe)
}