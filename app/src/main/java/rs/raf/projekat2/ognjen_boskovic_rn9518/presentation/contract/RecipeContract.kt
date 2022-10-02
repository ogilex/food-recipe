package rs.raf.projekat2.ognjen_boskovic_rn9518.presentation.contract

import androidx.lifecycle.LiveData
import rs.raf.projekat2.ognjen_boskovic_rn9518.data.models.local.Recipe
import rs.raf.projekat2.ognjen_boskovic_rn9518.presentation.util.RecipeFilter
import rs.raf.projekat2.ognjen_boskovic_rn9518.presentation.view.states.IngredientState
import rs.raf.projekat2.ognjen_boskovic_rn9518.presentation.view.states.RecipeState
import java.util.*

interface RecipeContract {

    interface ViewModel {

        val recipeState: LiveData<RecipeState>
        val ingredientsState: LiveData<IngredientState>
        val savedRecipeState: LiveData<RecipeState>

        fun fetchRecipe(recipe: String, page: String)
        fun getRecipes(filter: RecipeFilter)
        fun getSavedRecipes()
        fun saveRecipe(recipe: Recipe, category: String, date: Date)
        fun deleteRecipes()

        fun fetchIngredients(recipe_id: String)
        fun getIngredients(recipe_id: String)
    }
}