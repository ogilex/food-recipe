package rs.raf.projekat2.ognjen_boskovic_rn9518.data.repositories.recipes

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat2.ognjen_boskovic_rn9518.data.models.local.Ingredients
import rs.raf.projekat2.ognjen_boskovic_rn9518.data.models.local.Recipe
import rs.raf.projekat2.ognjen_boskovic_rn9518.data.models.local.Resource
import rs.raf.projekat2.ognjen_boskovic_rn9518.data.models.local.SavedRecipeEntity

interface RecipeRepository {

    fun fetchRecipes(recipe: String, page: String): Observable<Resource<Unit>>
    fun getRecipes(recipe: String): Observable<List<Recipe>>
    fun saveRecipe(recipe: SavedRecipeEntity): Completable
    fun getSavedRecipes(): Observable<List<Recipe>>
    fun fetchIngredient(recipe_id: String): Observable<Resource<Unit>>
    fun getIngredient(recipe_id: String): Observable<Ingredients>
    fun deleteRecipes(): Completable
}