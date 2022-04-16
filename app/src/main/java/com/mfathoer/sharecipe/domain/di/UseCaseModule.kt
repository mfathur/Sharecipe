package com.mfathoer.sharecipe.domain.di

import com.mfathoer.sharecipe.domain.usecase.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun provideDeleteBookmarkedRecipeUseCase(deleteBookmarkedRecipeUseCaseImpl: DeleteBookmarkedRecipeUseCaseImpl): DeleteBookmarkedRecipeUseCase

    @Binds
    abstract fun provideGetBookmarkedRecipeUseCase(getBookmarkedRecipesUseCase: GetBookmarkedRecipesUseCaseImpl): GetBookmarkedRecipesUseCase

    @Binds
    abstract fun provideInsertBookmarkedRecipeUseCase(insertBookmarkedRecipeUseCaseImpl: InsertBookmarkedRecipeUseCaseImpl): InsertBookmarkedRecipeUseCase

    @Binds
    abstract fun provideGetRecipesUseCase(getRecipesUseCaseImpl: GetRecipesUseCaseImpl): GetRecipesUseCase

    @Binds
    abstract fun provideGetDetailRecipeUseCase(getDetailRecipeUseCaseImpl: GetDetailRecipeUseCaseImpl): GetDetailRecipeUseCase

}