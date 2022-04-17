package com.mfathoer.sharecipe.data.source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarkDao {

    @Insert
    suspend fun insertBookmarkedRecipe(recipeEntity: RecipeEntity)

    @Query("SELECT * FROM recipe")
    fun getAllBookmarkedRecipes(): Flow<List<RecipeEntity>>

    @Delete
    suspend fun deleteBookmarkedRecipe(recipeEntity: RecipeEntity)

    @Query("SELECT * FROM recipe WHERE id=:recipeId")
    suspend fun getBookmarkedRecipeById(recipeId: Int): RecipeEntity
}