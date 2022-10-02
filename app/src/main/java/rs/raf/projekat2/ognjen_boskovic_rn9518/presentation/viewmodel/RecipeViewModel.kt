package rs.raf.projekat2.ognjen_boskovic_rn9518.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import rs.raf.projekat2.ognjen_boskovic_rn9518.data.models.local.Recipe
import rs.raf.projekat2.ognjen_boskovic_rn9518.data.models.local.Resource
import rs.raf.projekat2.ognjen_boskovic_rn9518.data.models.local.SavedRecipeEntity
import rs.raf.projekat2.ognjen_boskovic_rn9518.data.repositories.recipes.RecipeRepository
import rs.raf.projekat2.ognjen_boskovic_rn9518.presentation.contract.RecipeContract
import rs.raf.projekat2.ognjen_boskovic_rn9518.presentation.util.RecipeFilter
import rs.raf.projekat2.ognjen_boskovic_rn9518.presentation.view.states.IngredientState
import rs.raf.projekat2.ognjen_boskovic_rn9518.presentation.view.states.RecipeState
import timber.log.Timber
import java.util.*

class RecipeViewModel(
    private val recipeRepository: RecipeRepository
): ViewModel(), RecipeContract.ViewModel {

    private val subscriptions = CompositeDisposable()
    override val recipeState: MutableLiveData<RecipeState> = MutableLiveData()
    override val savedRecipeState: MutableLiveData<RecipeState> = MutableLiveData()
    override val ingredientsState: MutableLiveData<IngredientState> = MutableLiveData()
    private val publishSubject: PublishSubject<RecipeFilter> = PublishSubject.create()



    init {
        val subscription = publishSubject
            .switchMap {
                recipeRepository
                    .getRecipes(it.recipe)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnError {
                        Timber.e("Error in publish subject")
                        Timber.e(it)
                    }
            }
            .subscribe(
                {
                    recipeState.value = RecipeState.Success(it)
                },
                {
                    recipeState.value = RecipeState.Error("Error happened while getting data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun fetchRecipe(recipe: String, page: String) {
        val subscription = recipeRepository
            .fetchRecipes(recipe, page)
            .startWith(Resource.Loading())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    when(it) {
                        is Resource.Loading -> recipeState.value = RecipeState.Loading
                        is Resource.Success -> recipeState.value = RecipeState.DataFetched
                        is Resource.Error -> recipeState.value = RecipeState.Error("Error happened while fetching data from the server")
                    }
                },
                {
                    recipeState.value = RecipeState.Error("Error happened while fetching data from the server")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getRecipes(filter: RecipeFilter) {
        publishSubject.onNext(filter)
    }

    override fun getSavedRecipes() {
        val subscription = recipeRepository
            .getSavedRecipes()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    savedRecipeState.value = RecipeState.Success(it)
                },
                {
                    savedRecipeState.value = RecipeState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun saveRecipe(recipe: Recipe, category: String, date: Date) {
        val subscription = recipeRepository
            .saveRecipe(
                SavedRecipeEntity(
                    room_id = 0,
                    id = recipe.id,
                    title = recipe.title,
                    publisher = recipe.publisher,
                    recipe_id = recipe.recipe_id,
                    category = category,
                    date = date,
                    imageUrl = recipe.imageUrl,
                    socialRank = recipe.socialRank
                )
            )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Timber.d("Recipe saved")
                },
                {
                    savedRecipeState.value = RecipeState.Error("Error happened while updating data in db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun deleteRecipes() {
        val subscription = recipeRepository
            .deleteRecipes()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Timber.d("Deleted")
                },
                {
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun fetchIngredients(recipe_id: String) {
        val subscription = recipeRepository
            .fetchIngredient(recipe_id)
            .startWith(Resource.Loading())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    when(it) {
                        is Resource.Loading -> ingredientsState.value = IngredientState.Loading
                        is Resource.Success -> ingredientsState.value = IngredientState.DataFetched
                        is Resource.Error -> ingredientsState.value = IngredientState.Error("Error happened while fetching data from the server")
                    }
                },
                {
                    ingredientsState.value = IngredientState.Error("Error happened while fetching data from the server")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getIngredients(recipe_id: String) {
        val subscription = recipeRepository
            .getIngredient(recipe_id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    ingredientsState.value = IngredientState.Success(it)
                },
                {
                    ingredientsState.value = IngredientState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun onCleared() {
        super.onCleared()
        subscriptions.dispose()
    }
}