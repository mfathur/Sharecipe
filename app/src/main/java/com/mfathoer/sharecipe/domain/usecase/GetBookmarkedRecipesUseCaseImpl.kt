package com.mfathoer.sharecipe.domain.usecase

import com.mfathoer.sharecipe.data.Repository
import com.mfathoer.sharecipe.domain.model.Recipe
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBookmarkedRecipesUseCaseImpl @Inject constructor(private val repository: Repository) :
    GetBookmarkedRecipesUseCase {
    override suspend fun invoke(): Flow<List<Recipe>> {
        return repository.getAllBookmarkedRecipes()
    }
}