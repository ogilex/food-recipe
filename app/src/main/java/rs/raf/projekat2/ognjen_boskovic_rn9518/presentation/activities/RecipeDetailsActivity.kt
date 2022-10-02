package rs.raf.projekat2.ognjen_boskovic_rn9518.presentation.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.projekat2.ognjen_boskovic_rn9518.data.models.local.Recipe
import rs.raf.projekat2.ognjen_boskovic_rn9518.databinding.ActivityRecipeDetailsBinding
import rs.raf.projekat2.ognjen_boskovic_rn9518.presentation.contract.RecipeContract
import rs.raf.projekat2.ognjen_boskovic_rn9518.presentation.view.states.IngredientState
import rs.raf.projekat2.ognjen_boskovic_rn9518.presentation.viewmodel.RecipeViewModel

class RecipeDetailsActivity : AppCompatActivity() {
    private val recipeViewModel: RecipeContract.ViewModel by viewModel<RecipeViewModel>()
    private lateinit var recipe: Recipe
    private lateinit var binding: ActivityRecipeDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recipe = intent.getSerializableExtra(MainActivity.RECIPE_KEY) as Recipe
        binding = ActivityRecipeDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recipeViewModel.getIngredients(recipe.recipe_id)
        recipeViewModel.fetchIngredients(recipe.recipe_id)
        init()
    }

    private fun init(){
        initView()
        initListeners()
        initObservers()
    }

    private fun initView(){
        Glide.with(binding.root).load(recipe.imageUrl).into(binding.recipeImage)
        binding.recipeTitle.text = recipe.title

        //binding.recipeSocialRank.text = recipe.socialRank.toDouble().roundToInt().toString()
    }

    private fun initObservers(){
        recipeViewModel.ingredientsState.observe(this, Observer {
            renderIngredientState(it)
        })
    }

    private fun initListeners(){
        binding.recipeSaveBtn.setOnClickListener {
            val intent = Intent(this, SaveRecipeActivity::class.java)
            intent.putExtra(MainActivity.RECIPE_KEY, recipe)
            startActivity(intent)
        }
    }

    private fun renderIngredientState(state: IngredientState) {
        when(state){
            is IngredientState.Success -> {
                val ingredients = state.ingredients
                val ingredientsContainer = binding.ingredientsContainer
                if(ingredientsContainer.childCount == 0)
                    for (ingredient in ingredients.ingredients){
                        val textView = TextView(this)
                        textView.text = ingredient
                        ingredientsContainer.addView(textView)
                    }
            }
            is IngredientState.Error -> {
                //Timber.e("Error")
            }
            is IngredientState.DataFetched -> {
                //Timber.e("Data Fetched")
            }
            is IngredientState.Loading -> {
                //Timber.e("Loading")
            }
        }
    }
}