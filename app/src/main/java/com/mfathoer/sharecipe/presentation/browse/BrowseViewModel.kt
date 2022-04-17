package com.mfathoer.sharecipe.presentation.browse

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mfathoer.sharecipe.data.LoadResult
import com.mfathoer.sharecipe.domain.model.Recipe
import com.mfathoer.sharecipe.domain.usecase.GetRecipesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BrowseViewModel @Inject constructor(private val getRecipesUseCase: GetRecipesUseCase) :
    ViewModel() {

    init {
        searchRecipes("apple")
    }

    private val _recipes =
        MutableLiveData<LoadResult<List<Recipe>>>(LoadResult.Loading())

    val recipes: LiveData<LoadResult<List<Recipe>>>
        get() = _recipes

    fun searchRecipes(query: String) = viewModelScope.launch {
        try {
            val result = LoadResult.Success(getRecipesUseCase(query))
            Log.d("recipes", result.toString())
            _recipes.postValue(result)
        } catch (e: Exception) {
            _recipes.postValue(LoadResult.Error(e.message.toString()))
        }
    }

}