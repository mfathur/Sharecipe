package com.mfathoer.sharecipe.domain.utils

import com.mfathoer.sharecipe.data.source.local.RecipeEntity
import com.mfathoer.sharecipe.data.source.remote.response.RecipeResponse
import com.mfathoer.sharecipe.domain.model.Recipe

object DataMapper {

    fun mapRecipeResponseToDomain(recipeResponse: RecipeResponse): Recipe {
        return Recipe(
            id = recipeResponse.id ?: 0,
            title = recipeResponse.title ?: "",
            imageUrl = recipeResponse.imageUrl ?: "",
            sourceUrl = recipeResponse.sourceUrl ?: "",
            summary = recipeResponse.summary ?: ""
        )
    }

    fun mapRecipeResponsesToDomains(recipeResponses: List<RecipeResponse?>): List<Recipe> {
        val list = ArrayList<Recipe>()
        recipeResponses.forEach { recipeResponse ->
            val recipe = mapRecipeResponseToDomain(recipeResponse!!)
            list.add(recipe)
        }

        return list
    }

    fun mapRecipeDomainToEntity(recipe: Recipe): RecipeEntity {
        return RecipeEntity(
            id = recipe.id,
            title = recipe.title,
            summary = recipe.summary,
            imageUrl = recipe.imageUrl,
            sourceUrl = recipe.sourceUrl
        )
    }

    fun mapRecipeEntityToDomain(recipeEntity: RecipeEntity): Recipe {
        return Recipe(
            id = recipeEntity.id,
            title = recipeEntity.title,
            imageUrl = recipeEntity.imageUrl,
            summary = recipeEntity.summary,
            sourceUrl = recipeEntity.sourceUrl
        )
    }


    fun mapRecipeEntitiesToDomains(recipeEntities: List<RecipeEntity>): List<Recipe> {
        val list = ArrayList<Recipe>()

        recipeEntities.forEach { recipeEntity ->
            val recipe = mapRecipeEntityToDomain(recipeEntity)
            list.add(recipe)
        }

        return list
    }

}