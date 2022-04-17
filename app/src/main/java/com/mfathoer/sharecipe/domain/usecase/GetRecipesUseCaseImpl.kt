package com.mfathoer.sharecipe.domain.usecase

import com.mfathoer.sharecipe.data.Repository
import com.mfathoer.sharecipe.domain.model.Recipe
import javax.inject.Inject

class GetRecipesUseCaseImpl @Inject constructor(private val repository: Repository) :
    GetRecipesUseCase {
    override suspend fun invoke(query: String): List<Recipe> = repository.searchRecipes(query)
}