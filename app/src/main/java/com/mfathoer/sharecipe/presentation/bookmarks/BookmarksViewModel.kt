package com.mfathoer.sharecipe.presentation.bookmarks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mfathoer.sharecipe.data.LoadResult
import com.mfathoer.sharecipe.domain.model.Recipe
import com.mfathoer.sharecipe.domain.usecase.GetBookmarkedRecipesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarksViewModel @Inject constructor(private val getBookmarkedRecipesUseCase: GetBookmarkedRecipesUseCase) :
    ViewModel() {

    init {
        getBookmarkedRecipes()
    }

    private val _recipes = MutableLiveData<LoadResult<List<Recipe>>>(LoadResult.Loading())

    val recipes: LiveData<LoadResult<List<Recipe>>>
        get() = _recipes

    private fun getBookmarkedRecipes() = viewModelScope.launch {
        try {
            getBookmarkedRecipesUseCase.invoke().collectLatest {
                _recipes.postValue(LoadResult.Success(it))
            }
        } catch (e: Exception) {
            _recipes.postValue(LoadResult.Error(e.message.toString()))
        }
    }

}