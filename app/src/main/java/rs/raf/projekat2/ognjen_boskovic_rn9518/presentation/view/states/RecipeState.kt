package rs.raf.projekat2.ognjen_boskovic_rn9518.presentation.view.states

import rs.raf.projekat2.ognjen_boskovic_rn9518.data.models.local.Recipe

sealed class RecipeState {
    object Loading: RecipeState()
    object DataFetched: RecipeState()
    data class Success(val recipes: List<Recipe>): RecipeState()
    data class Error(val message: String): RecipeState()
}