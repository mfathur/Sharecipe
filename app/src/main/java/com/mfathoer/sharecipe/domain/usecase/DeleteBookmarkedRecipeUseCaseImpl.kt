package com.mfathoer.sharecipe.domain.usecase

import com.mfathoer.sharecipe.data.Repository
import javax.inject.Inject

class DeleteBookmarkedRecipeUseCaseImpl @Inject constructor(private val repository: Repository) :
    DeleteBookmarkedRecipeUseCase {
}