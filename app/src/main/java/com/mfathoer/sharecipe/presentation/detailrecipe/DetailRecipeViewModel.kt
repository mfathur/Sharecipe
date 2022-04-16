package com.mfathoer.sharecipe.presentation.detailrecipe

import androidx.lifecycle.ViewModel
import com.mfathoer.sharecipe.domain.usecase.DeleteBookmarkedRecipeUseCase
import com.mfathoer.sharecipe.domain.usecase.GetDetailRecipeUseCase
import com.mfathoer.sharecipe.domain.usecase.InsertBookmarkedRecipeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailRecipeViewModel @Inject constructor(
    private val getDetailRecipeUseCaseImpl: GetDetailRecipeUseCase,
    private val insertBookmarkedRecipeUseCase: InsertBookmarkedRecipeUseCase,
    private val deleteBookmarkedRecipeUseCaseImpl: DeleteBookmarkedRecipeUseCase
) :
    ViewModel() {
}