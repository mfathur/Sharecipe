package com.mfathoer.sharecipe.domain.usecase

import com.mfathoer.sharecipe.data.Repository
import com.mfathoer.sharecipe.domain.model.Recipe
import javax.inject.Inject

class InsertBookmarkedRecipeUseCaseImpl @Inject constructor(private val repository: Repository) :
    InsertBookmarkedRecipeUseCase {
    override suspend fun invoke(recipe: Recipe) {
        repository.insertBookmarkedRecipe(recipe)
    }
}