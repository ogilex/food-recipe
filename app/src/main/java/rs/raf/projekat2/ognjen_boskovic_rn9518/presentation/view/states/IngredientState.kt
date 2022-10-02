package rs.raf.projekat2.ognjen_boskovic_rn9518.presentation.view.states

import rs.raf.projekat2.ognjen_boskovic_rn9518.data.models.local.Ingredients

sealed class IngredientState {
    object Loading: IngredientState()
    object DataFetched: IngredientState()
    data class Success(val ingredients: Ingredients): IngredientState()
    data class Error(val message: String): IngredientState()
}