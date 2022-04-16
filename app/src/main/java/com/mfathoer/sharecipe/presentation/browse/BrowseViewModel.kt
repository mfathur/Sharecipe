package com.mfathoer.sharecipe.presentation.browse

import androidx.lifecycle.ViewModel
import com.mfathoer.sharecipe.domain.usecase.GetRecipesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BrowseViewModel @Inject constructor(private val getRecipesUseCase: GetRecipesUseCase): ViewModel() {
}