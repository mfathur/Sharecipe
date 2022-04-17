package com.mfathoer.sharecipe.data

import com.mfathoer.sharecipe.data.source.local.LocalDataSource
import com.mfathoer.sharecipe.data.source.remote.RemoteDataSource
import com.mfathoer.sharecipe.domain.model.Recipe
import com.mfathoer.sharecipe.domain.utils.DataMapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.Default,
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : Repository {
    override suspend fun searchRecipes(query: String): List<Recipe> {
        return withContext(ioDispatcher) {
            return@withContext DataMapper.mapRecipeResponsesToDomains(
                remoteDataSource.searchRecipes(
                    query
                )
            )
        }
    }

    override suspend fun getDetailRecipe(recipeId: Int): Recipe {
        return withContext(ioDispatcher) {
            val recipeResponse = remoteDataSource.getDetailRecipe(recipeId)
            return@withContext DataMapper.mapRecipeResponseToDomain(recipeResponse)
        }
    }

    override suspend fun getAllBookmarkedRecipes(): Flow<List<Recipe>> {
        return withContext(ioDispatcher) {
            return@withContext localDataSource.getAllBookmarkedRecipe().map {
                DataMapper.mapRecipeEntitiesToDomains(it)
            }
        }
    }

    override suspend fun getBookmarkedRecipeById(recipeId: Int): Recipe {
        return withContext(ioDispatcher) {
            return@withContext DataMapper.mapRecipeEntityToDomain(
                localDataSource.getBookmarkedRecipeById(
                    recipeId
                )
            )
        }
    }

    override suspend fun deleteBookmarkedRecipe(recipe: Recipe) {
        withContext(ioDispatcher) {
            localDataSource.deleteBookmarkedRecipe(DataMapper.mapRecipeDomainToEntity(recipe))
        }
    }

    override suspend fun insertBookmarkedRecipe(recipe: Recipe) {
        withContext(ioDispatcher) {
            localDataSource.insertBookmarkedRecipe(DataMapper.mapRecipeDomainToEntity(recipe))
        }
    }
}