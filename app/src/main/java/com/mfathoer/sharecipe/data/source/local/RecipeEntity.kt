package com.mfathoer.sharecipe.data.source.local

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipe")
data class RecipeEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: Int = 1,

    @NonNull
    @ColumnInfo(name = "title")
    val title: String,

    @NonNull
    @ColumnInfo(name = "image_url")
    val imageUrl: String,

    @NonNull
    @ColumnInfo(name = "summary")
    val summary: String,

    @NonNull
    @ColumnInfo(name = "source_url")
    val sourceUrl: String
)
