package com.mfathoer.sharecipe.domain.usecase

import com.mfathoer.sharecipe.data.Repository
import javax.inject.Inject

class GetRecipesUseCaseImpl @Inject constructor(private val repository: Repository): GetRecipesUseCase {
}