package com.mfathoer.sharecipe.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class SearchRecipesResponse(

    @field:SerializedName("number")
    val number: Int? = null,

    @field:SerializedName("totalResults")
    val totalResults: Int? = null,

    @field:SerializedName("offset")
    val offset: Int? = null,

    @field:SerializedName("results")
    val results: List<RecipeResponse?>? = null
)

data class RecipeResponse(

    @field:SerializedName("image")
    val imageUrl: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("summary")
    val summary: String? = null,

    @field:SerializedName("spoonacularSourceUrl")
    val sourceUrl: String? = null,

    )
