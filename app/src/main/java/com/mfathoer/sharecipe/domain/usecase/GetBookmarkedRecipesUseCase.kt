package com.mfathoer.sharecipe.domain.usecase

import com.mfathoer.sharecipe.domain.model.Recipe
import kotlinx.coroutines.flow.Flow

interface GetBookmarkedRecipesUseCase {
    suspend operator fun invoke(): Flow<List<Recipe>>
}