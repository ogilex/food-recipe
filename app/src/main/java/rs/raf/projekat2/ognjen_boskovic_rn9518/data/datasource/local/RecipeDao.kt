package rs.raf.projekat2.ognjen_boskovic_rn9518.data.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat2.ognjen_boskovic_rn9518.data.models.local.IngredientsEntity
import rs.raf.projekat2.ognjen_boskovic_rn9518.data.models.local.RecipeEntity
import rs.raf.projekat2.ognjen_boskovic_rn9518.data.models.local.SavedRecipeEntity

@Dao
abstract class RecipeDao {

    @Insert( onConflict =  OnConflictStrategy.REPLACE )
    abstract fun insertAll(entities: List<RecipeEntity>): Completable

    @Insert( onConflict = OnConflictStrategy.IGNORE )
    abstract fun saveRecipe(recipe: SavedRecipeEntity): Completable

    @Query("SELECT * FROM savedRecipes")
    abstract fun getSavedRecipes(): Observable<List<SavedRecipeEntity>>

    @Query("SELECT * FROM recipe")
    abstract fun getRecipes(): Observable<List<RecipeEntity>>

    @Query("DELETE FROM recipe")
    abstract fun deleteAll(): Completable

    @Query("SELECT * FROM ingredient WHERE recipe_id == :recipe_id")
    abstract fun getIngredientsForRecipe(recipe_id: String): Observable<IngredientsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertIngredient(ingredient: IngredientsEntity): Completable

}