package com.tasty.recipesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.tasty.recipesapp.databinding.ActivityMainBinding
import com.tasty.recipesapp.dto.RecipeDTO
import com.tasty.recipesapp.model.RecipeModel
import com.tasty.recipesapp.viewModel.RecipeListViewModel
import com.tasty.recipesapp.ui.RecipeListAdapter

class MainActivity : AppCompatActivity() {

    companion object{
        val TAG = MainActivity::class.java.simpleName
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
        val view = ActivityMainBinding.inflate(layoutInflater)
        setContentView(view.root)
        val name = intent.extras?.getString("name")
        val recipes: Array<RecipeModel> = emptyArray()
        val myAdapter = RecipeListAdapter(recipes)
        view.recipeList.adapter = myAdapter
        view.recipeList.layoutManager = LinearLayoutManager(this)

        val viewModel: RecipeListViewModel by viewModels()
        val liveData = viewModel.liveData
        liveData.observe(this) {
            myAdapter.recipes = it
            myAdapter.notifyDataSetChanged()
            it.forEach {
                Log.d(TAG, it.toString())
            }
        }
        viewModel.readAllRecipes(this)
    }

}