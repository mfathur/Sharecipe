package com.mfathoer.sharecipe.presentation.detailrecipe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mfathoer.sharecipe.data.LoadResult
import com.mfathoer.sharecipe.domain.model.Recipe
import com.mfathoer.sharecipe.domain.usecase.DeleteBookmarkedRecipeUseCase
import com.mfathoer.sharecipe.domain.usecase.GetDetailBookmarkedRecipeUseCase
import com.mfathoer.sharecipe.domain.usecase.GetDetailRecipeUseCase
import com.mfathoer.sharecipe.domain.usecase.InsertBookmarkedRecipeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailRecipeViewModel @Inject constructor(
    private val getDetailRecipeUseCase: GetDetailRecipeUseCase,
    private val getDetailBookmarkedRecipeUseCase: GetDetailBookmarkedRecipeUseCase,
    private val insertBookmarkedRecipeUseCase: InsertBookmarkedRecipeUseCase,
    private val deleteBookmarkedRecipeUseCase: DeleteBookmarkedRecipeUseCase,
) : ViewModel() {

    private val _recipe = MutableLiveData<LoadResult<Recipe>>(LoadResult.Loading())

    val recipe: LiveData<LoadResult<Recipe>>
        get() = _recipe

    private val _bookmarkStatus = MutableLiveData(false)

    val bookmarkStatus: LiveData<Boolean>
        get() = _bookmarkStatus

    private fun deleteBookmarkedRecipe(recipe: Recipe) = viewModelScope.launch {
        deleteBookmarkedRecipeUseCase(recipe)
    }

    private fun insertBookmarkedRecipe(recipe: Recipe) = viewModelScope.launch {
        insertBookmarkedRecipeUseCase(recipe)
    }

    fun changeBookmarkStatus() {
        val isBookmarked = bookmarkStatus.value!!

        recipe.value?.data?.let {
            if (isBookmarked) {
                deleteBookmarkedRecipe(it)
            } else {
                insertBookmarkedRecipe(it)
            }
            _bookmarkStatus.value = !_bookmarkStatus.value!!
        }

    }


    fun getDetailBookmarkedRecipeById(recipeId: Int) = viewModelScope.launch {
        try {
            val recipe = getDetailBookmarkedRecipeUseCase(recipeId)
            _recipe.postValue(LoadResult.Success(recipe))
            _bookmarkStatus.value = true
        } catch (e: Exception) {
            _recipe.postValue(LoadResult.Error(e.message.toString()))
        }
    }

    fun getDetailRecipe(recipeId: Int) = viewModelScope.launch {
        try {
            val recipe = getDetailRecipeUseCase(recipeId)
            _recipe.postValue(LoadResult.Success(recipe))
            checkBookmarkStatus(recipeId)
        } catch (e: Exception) {
            _recipe.postValue(LoadResult.Error(e.message.toString()))
        }
    }

    private fun checkBookmarkStatus(recipeId: Int) = viewModelScope.launch {
        try {
//            data found in database
            getDetailBookmarkedRecipeUseCase(recipeId)
            _bookmarkStatus.value = true
        } catch (e: Exception) {
//            data not found in database
            _bookmarkStatus.value = false
        }
    }

}