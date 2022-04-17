package com.mfathoer.sharecipe.domain.usecase

import com.mfathoer.sharecipe.domain.model.Recipe

interface DeleteBookmarkedRecipeUseCase {
    suspend operator fun invoke(recipe: Recipe)
}