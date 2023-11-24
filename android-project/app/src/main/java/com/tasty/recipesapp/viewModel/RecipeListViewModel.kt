package com.tasty.recipesapp.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tasty.recipesapp.model.RecipeModel
import com.tasty.recipesapp.repo.RecipeRepository
import com.tasty.recipesapp.repo.dao.RecipeDao
import com.tasty.recipesapp.repo.database.RecipeDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecipeListViewModel: ViewModel() {

    val liveData = MutableLiveData<Array<RecipeModel>>()

    fun readAllRecipes(context: Context){
        viewModelScope.launch (Dispatchers.IO){
            val list = RecipeRepository(context).getAllRecipes()
            val models = list.map {
                RecipeModel(it.name)
            }
            viewModelScope.launch{
                liveData.value = models.toTypedArray()
            }
        }
    }
}