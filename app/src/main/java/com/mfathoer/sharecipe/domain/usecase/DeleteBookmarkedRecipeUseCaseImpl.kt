package com.mfathoer.sharecipe.domain.usecase

import com.mfathoer.sharecipe.data.Repository
import com.mfathoer.sharecipe.domain.model.Recipe
import javax.inject.Inject

class DeleteBookmarkedRecipeUseCaseImpl @Inject constructor(private val repository: Repository) :
    DeleteBookmarkedRecipeUseCase {
    override suspend fun invoke(recipe: Recipe) {
        repository.deleteBookmarkedRecipe(recipe)
    }
}