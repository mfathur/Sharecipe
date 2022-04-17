package com.mfathoer.sharecipe.domain.usecase

import com.mfathoer.sharecipe.domain.model.Recipe

interface InsertBookmarkedRecipeUseCase {
    suspend operator fun invoke(recipe: Recipe)
}