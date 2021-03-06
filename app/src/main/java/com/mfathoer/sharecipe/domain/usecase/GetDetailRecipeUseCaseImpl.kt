package com.mfathoer.sharecipe.domain.usecase

import com.mfathoer.sharecipe.data.Repository
import com.mfathoer.sharecipe.domain.model.Recipe
import javax.inject.Inject

class GetDetailRecipeUseCaseImpl @Inject constructor(private val repository: Repository) :
    GetDetailRecipeUseCase {
    override suspend fun invoke(recipeId: Int): Recipe {
        return repository.getDetailRecipe(recipeId)
    }
}