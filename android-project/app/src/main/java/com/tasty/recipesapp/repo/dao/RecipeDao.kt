package com.tasty.recipesapp.repo.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.tasty.recipesapp.entity.RecipeEntity

@Dao
 interface RecipeDao {
    @Insert
     fun insertRecipe(recipe: RecipeEntity)
    @Query("SELECT * FROM recipe WHERE internalId = :id")
     fun getRecipeById(id: Long): RecipeEntity?
    @Query("SELECT * FROM recipe")
    fun getAllRecipes(): List<RecipeEntity>
    @Delete
     fun deleteRecipe(recipe: RecipeEntity)
}