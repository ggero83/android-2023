package com.tasty.recipesapp.repo


import android.content.Context
import com.google.gson.Gson
import com.tasty.recipesapp.dto.RecipeDTO
import com.tasty.recipesapp.dto.RecipeResultDTO
import com.tasty.recipesapp.entity.RecipeEntity
import com.tasty.recipesapp.model.RecipeModel
import com.tasty.recipesapp.repo.dao.RecipeDao
import com.tasty.recipesapp.repo.database.RecipeDatabase
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.Reader
import java.io.Writer
import kotlin.coroutines.CoroutineContext


class RecipeRepository(val context: Context) {

    val gson = Gson()

    suspend fun insertRecipe(recipe: RecipeEntity) {
        RecipeDatabase.getDatabase(context).recipeDao().insertRecipe(recipe)
    }

    suspend fun readRecipes(): Array<RecipeDTO> {
        val file = context.resources.openRawResource(com.tasty.recipesapp.R.raw.all_recipes)

        try {
            val reader: Reader = BufferedReader(InputStreamReader(file, "UTF-8"))
            val result = gson.fromJson<RecipeResultDTO>(reader, RecipeResultDTO::class.java)
            return result.results
        } finally {
            file.close()
        }

    }
      fun getAllRecipes(): List<RecipeDTO> {
        return RecipeDatabase.getDatabase(context).recipeDao().getAllRecipes().map {
            val jsonObject = JSONObject(it.json)
            jsonObject.apply { put("id", it.internalId) }
            gson.fromJson(jsonObject.toString(), RecipeDTO::class.java)
        }
    }
}