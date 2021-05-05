package com.team03.cocktailrecipesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.team03.cocktailrecipesapp.recipes.GetRecipesErrorListener
import com.team03.cocktailrecipesapp.recipes.GetRecipesListener

import com.team03.cocktailrecipesapp.ui.login.LoginActivity
import com.team03.cocktailrecipesapp.recipes.Recipe
import kotlinx.android.synthetic.main.progress_indicator.*
import kotlinx.android.synthetic.main.error_msg_indicator.*
import kotlinx.android.synthetic.main.trending_cocktail_list.*
import kotlinx.android.synthetic.main.trending_cocktail_list_card.view.*

//TODO: -> move to shared preferences
var userId = 0;
var userName = "";

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //TODO: -> get info from shared preferences
        if (userId == 0) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        else {
            setContentView(R.layout.activity_main)

            // Get recipes from server and inflate list
            getTrendingRecipesList()
        }
    }

    fun getTrendingRecipesList() {
        val server = serverAPI(applicationContext)
        val listener = GetRecipesListener(::onSuccessfulGetRecipes)
        val errorListener = GetRecipesErrorListener(::onFailedGetRecipes)
        server.getRecipes(listener, errorListener)
    }

    fun onSuccessfulGetRecipes(recipe_list: List<Recipe>) {
        progressBar.visibility = View.GONE
        fillTrendingRecipesList(recipe_list)
    }

    fun onFailedGetRecipes() {
        txtViewErrorMsg.text = resources.getString(R.string.failed_to_load_recipes_from_server)
        txtViewErrorMsgContainer.visibility = View.VISIBLE
        Toast.makeText(applicationContext, resources.getString(R.string.failed_to_load_recipes_from_server), Toast.LENGTH_LONG).show()
    }

    fun fillTrendingRecipesList(recipe_list: List<Recipe>) {
        recipe_list.forEach() { recipe ->
            val recipeCard = LayoutInflater.from(this).inflate(R.layout.trending_cocktail_list_card, trending_cocktail_list, false)
            recipeCard.cocktail_name.text = recipe.name
            recipeCard.cocktail_ratings.text = recipe.times_rated.toString()
            recipeCard.cocktail_rating_bar.rating = recipe.rating
            recipeCard.cocktail_difficulty.text =  recipe.difficulty.toString()
            val preparationTime: String = recipe.preptime_minutes.toString() + " minutes"
            recipeCard.cocktail_preparation_time.text = preparationTime
            /* TODO: recipeCard.cocktail_image */
            trending_cocktail_list.addView(recipeCard)
        }
    }


    fun loginOnClick(view: View){
            val intent = Intent(this, UserProfile::class.java)
            startActivity(intent)
    }
}