package com.mfathoer.sharecipe.presentation.bookmarks

import androidx.lifecycle.ViewModel
import com.mfathoer.sharecipe.domain.usecase.GetBookmarkedRecipesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BookmarksViewModel @Inject constructor(private val getBookmarkedRecipesUseCase: GetBookmarkedRecipesUseCase) :
    ViewModel() {
}