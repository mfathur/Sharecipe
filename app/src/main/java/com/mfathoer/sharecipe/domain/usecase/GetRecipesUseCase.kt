package com.mfathoer.sharecipe.domain.usecase

import com.mfathoer.sharecipe.domain.model.Recipe

interface GetRecipesUseCase {
    suspend operator fun invoke(query: String): List<Recipe>
}