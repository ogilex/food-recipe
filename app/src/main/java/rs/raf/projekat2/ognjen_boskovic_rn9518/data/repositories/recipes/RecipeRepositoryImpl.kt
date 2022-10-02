package rs.raf.projekat2.ognjen_boskovic_rn9518.data.repositories.recipes

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat2.ognjen_boskovic_rn9518.data.datasource.local.RecipeDao
import rs.raf.projekat2.ognjen_boskovic_rn9518.data.datasource.remote.RecipeService
import rs.raf.projekat2.ognjen_boskovic_rn9518.data.models.local.*

class RecipeRepositoryImpl(
    private val localDataSource: RecipeDao,
    private val remoteDataSource: RecipeService
): RecipeRepository {

    override fun fetchRecipes(recipe: String, page: String): Observable<Resource<Unit>> {
        return remoteDataSource
            .getPage(recipe, page)
            .doOnNext {

                val recipes = it.recipes.map {
                    RecipeEntity(
                        id = it._id,
                        title = it.title,
                        socialRank = it.social_rank,
                        imageUrl = it.image_url,
                        page = page,
                        recipe_id = it.recipe_id,
                        publisher = it.publisher
                    )
                }

                localDataSource.insertAll(recipes).blockingAwait()

            }
            .map {
                Resource.Success(Unit)
            }
    }

    override fun getRecipes(recipe: String): Observable<List<Recipe>> {
        return localDataSource
            .getRecipes()
            .map {
                it.map {
                    Recipe(
                        id = it.id,
                        page = it.page,
                        title = it.title,
                        imageUrl = it.imageUrl,
                        socialRank = it.socialRank,
                        publisher = it.publisher,
                        recipe_id = it.recipe_id,
                        ingredients = listOf()
                    )
                }
            }
    }

    override fun saveRecipe(recipe: SavedRecipeEntity): Completable {
        return localDataSource.saveRecipe(recipe)
    }

    override fun getSavedRecipes(): Observable<List<Recipe>> {
        return localDataSource
            .getSavedRecipes()
            .map {
                it.map {
                    Recipe(
                        id = it.id,
                        title = it.title,
                        publisher = it.publisher,
                        recipe_id = it.recipe_id,
                        category = it.category,
                        date = it.date,
                        /*imageUrl = it.imageUrl,
                        socialRank = it.socialRank,*/
                        ingredients = listOf()
                    )
                }
            }
    }

    override fun fetchIngredient(recipe_id: String): Observable<Resource<Unit>> {
        return remoteDataSource
            .getIngredients(recipe_id)
            .doOnNext {

                val recipeIngredients = it.recipe
                val ingredientEntity = IngredientsEntity (
                    id = recipeIngredients._id,
                    ingredients = recipeIngredients.ingredients,
                    recipe_id = recipeIngredients.recipe_id
                )
                localDataSource.insertIngredient(ingredientEntity).blockingAwait()
            }
            .map {
                Resource.Success(Unit)
            }
    }

    override fun getIngredient(recipe_id: String): Observable<Ingredients> {
        return localDataSource
            .getIngredientsForRecipe(recipe_id)
            .map {
                Ingredients(
                    id = it.id,
                    ingredients = it.ingredients,
                    recipe_id = it.recipe_id
                )
            }
    }

    override fun deleteRecipes(): Completable {
        return localDataSource
            .deleteAll()
    }
}