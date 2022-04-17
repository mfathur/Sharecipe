package com.mfathoer.sharecipe.domain.usecase

import com.mfathoer.sharecipe.data.Repository
import com.mfathoer.sharecipe.domain.model.Recipe
import javax.inject.Inject

class GetDetailBookmarkedRecipeUseCaseImpl @Inject constructor(private val repository: Repository) :
    GetDetailBookmarkedRecipeUseCase {
    override suspend fun invoke(recipeId: Int): Recipe {
        return repository.getBookmarkedRecipeById(recipeId)
    }
}