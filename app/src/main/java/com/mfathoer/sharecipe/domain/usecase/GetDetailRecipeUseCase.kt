package com.mfathoer.sharecipe.domain.usecase

import com.mfathoer.sharecipe.domain.model.Recipe

interface GetDetailRecipeUseCase {
    suspend operator fun invoke(recipeId: Int): Recipe
}