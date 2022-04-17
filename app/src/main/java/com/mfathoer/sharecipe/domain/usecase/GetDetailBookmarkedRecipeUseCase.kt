package com.mfathoer.sharecipe.domain.usecase

import com.mfathoer.sharecipe.domain.model.Recipe

interface GetDetailBookmarkedRecipeUseCase {
    suspend operator fun invoke(recipeId: Int): Recipe
}