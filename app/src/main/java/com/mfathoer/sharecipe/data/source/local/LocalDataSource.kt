package com.mfathoer.sharecipe.data.source.local

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject


class LocalDataSource @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.Default,
    private val bookmarkDao: BookmarkDao
) {
    suspend fun getAllBookmarkedRecipe(): Flow<List<RecipeEntity>> {
        return withContext(ioDispatcher) {
            return@withContext bookmarkDao.getAllBookmarkedRecipes()
        }
    }

    suspend fun deleteBookmarkedRecipe(recipeEntity: RecipeEntity) {
        withContext(ioDispatcher) {
            bookmarkDao.deleteBookmarkedRecipe(recipeEntity)
        }
    }

    suspend fun insertBookmarkedRecipe(recipeEntity: RecipeEntity) {
        withContext(ioDispatcher) {
            bookmarkDao.insertBookmarkedRecipe(recipeEntity)
        }
    }

    suspend fun getBookmarkedRecipeById(recipeId: Int): RecipeEntity {
        return withContext(ioDispatcher) {
            return@withContext bookmarkDao.getBookmarkedRecipeById(recipeId)
        }
    }


}