package rs.raf.projekat2.ognjen_boskovic_rn9518.presentation.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.projekat2.ognjen_boskovic_rn9518.R
import rs.raf.projekat2.ognjen_boskovic_rn9518.data.models.local.Category
import rs.raf.projekat2.ognjen_boskovic_rn9518.databinding.ActivityMainBinding
import rs.raf.projekat2.ognjen_boskovic_rn9518.presentation.contract.RecipeContract
import rs.raf.projekat2.ognjen_boskovic_rn9518.presentation.util.RecipeFilter
import rs.raf.projekat2.ognjen_boskovic_rn9518.presentation.view.recycler.adapter.CategoryAdapter
import rs.raf.projekat2.ognjen_boskovic_rn9518.presentation.view.recycler.adapter.RecipeAdapter
import rs.raf.projekat2.ognjen_boskovic_rn9518.presentation.view.recycler.adapter.SavedRecipeAdapter
import rs.raf.projekat2.ognjen_boskovic_rn9518.presentation.view.recycler.diff.CategoryDiffItemCallBack
import rs.raf.projekat2.ognjen_boskovic_rn9518.presentation.view.recycler.diff.RecipeDiffItemCallBack
import rs.raf.projekat2.ognjen_boskovic_rn9518.presentation.view.recycler.diff.SavedRecipeDiffItemCallBack
import rs.raf.projekat2.ognjen_boskovic_rn9518.presentation.view.states.RecipeState
import rs.raf.projekat2.ognjen_boskovic_rn9518.presentation.viewmodel.RecipeViewModel

class MainActivity : AppCompatActivity() {
    private val recipeViewModel: RecipeContract.ViewModel by viewModel<RecipeViewModel>()
    private lateinit var search: String

    private lateinit var binding: ActivityMainBinding
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var recipeAdapter: RecipeAdapter
    private lateinit var savedRecipeAdapter: SavedRecipeAdapter

    private val categoryList: List<Category> = hardCodeList()

    companion object {
        const val RECIPE_KEY = "recipe"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init(){
        initRecycler()
        initObservers()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_main, menu)
        val menuItemSearch = menu?.findItem(R.id.search)
        menuItemSearch?.setOnActionExpandListener(object : MenuItem.OnActionExpandListener{
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                binding.categoryRecyclerView.visibility = View.GONE
                binding.savedRecycleView.visibility = View.GONE
                binding.recipeRecyclerView.visibility = View.VISIBLE
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                binding.categoryRecyclerView.visibility = View.VISIBLE
                binding.savedRecycleView.visibility = View.GONE
                binding.recipeRecyclerView.visibility = View.GONE
                recipeViewModel.deleteRecipes()
                return true
            }

        })

        val searchView: SearchView = menuItemSearch?.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    search = query
                    recipeViewModel.deleteRecipes()
                    recipeViewModel.getRecipes(RecipeFilter(query))
                    recipeViewModel.fetchRecipe(query, "1")
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

        val menuItemCategories = menu.findItem(R.id.categories)
        val menuItemSaved = menu.findItem(R.id.savedMenus)

        menuItemCategories.setOnMenuItemClickListener {
            binding.recipeRecyclerView.visibility = View.GONE
            binding.savedRecycleView.visibility = View.GONE
            binding.categoryRecyclerView.visibility = View.VISIBLE
            recipeViewModel.deleteRecipes()
            true
        }

        menuItemSaved.setOnMenuItemClickListener {
            recipeViewModel.getSavedRecipes()
            binding.recipeRecyclerView.visibility = View.GONE
            binding.categoryRecyclerView.visibility = View.GONE
            binding.savedRecycleView.visibility = View.VISIBLE
            recipeViewModel.deleteRecipes()
            true
        }

        return true
    }

    private fun initObservers(){
        recipeViewModel.recipeState.observe(this, Observer {
            renderState(it)
        })
        recipeViewModel.savedRecipeState.observe(this, Observer {
            renderSavedState(it)
        })
    }

    private fun initRecycler(){
        binding.categoryRecyclerView.layoutManager = LinearLayoutManager(this)
        categoryAdapter = CategoryAdapter(CategoryDiffItemCallBack()){
            binding.categoryRecyclerView.visibility = View.GONE
            binding.savedRecycleView.visibility = View.GONE
            binding.recipeRecyclerView.visibility = View.VISIBLE
            search = it.title
            recipeViewModel.getRecipes(RecipeFilter(it.title))
            for (i in 1..40){
                recipeViewModel.fetchRecipe(it.title, i.toString())
            }

        }
        binding.categoryRecyclerView.adapter = categoryAdapter
        categoryAdapter.submitList(categoryList)

        binding.recipeRecyclerView.layoutManager = LinearLayoutManager(this)
        recipeAdapter = RecipeAdapter(RecipeDiffItemCallBack()){
            val intent = Intent(this, RecipeDetailsActivity::class.java)
            intent.putExtra(RECIPE_KEY, it)
            startActivity(intent)
        }
        binding.recipeRecyclerView.adapter = recipeAdapter

        binding.savedRecycleView.layoutManager = LinearLayoutManager(this)
        savedRecipeAdapter = SavedRecipeAdapter(SavedRecipeDiffItemCallBack()){
            val intent = Intent(this, SavedRecipeDetailsActivity::class.java)
            intent.putExtra(RECIPE_KEY, it)
            startActivity(intent)
        }
        binding.savedRecycleView.adapter = savedRecipeAdapter
    }

    private fun renderSavedState(state: RecipeState?) {
        when(state){
            is RecipeState.Success -> {
                savedRecipeAdapter.submitList(state.recipes)
            }
            is RecipeState.Error -> {

            }
            is RecipeState.DataFetched -> {

            }
            is RecipeState.Loading -> {

            }
        }
    }

    private fun renderState(state: RecipeState) {
        when(state){
            is RecipeState.Success -> {
                recipeAdapter.submitList(state.recipes)
            }
            is RecipeState.Error -> {

            }
            is RecipeState.DataFetched -> {

            }
            is RecipeState.Loading -> {

            }
        }
    }

    private fun hardCodeList(): List<Category>{
        return listOf(
            Category(
                "1",
                "https://image.flaticon.com/icons/png/512/14/14480.png",
                "Barbecue"
            ),
            Category(
                "2",
                "https://image.flaticon.com/icons/png/512/348/348785.png",
                "Breakfast"),
            Category(
                "3",
                "https://image.flaticon.com/icons/png/512/1953/1953674.png",
                "Chicken"
            ),
            Category(
                "4",
                "https://image.flaticon.com/icons/png/512/821/821121.png",
                "Beef"),
            Category(
                "5",
                "https://image.flaticon.com/icons/png/512/3321/3321296.png",
                "Brunch"),
            Category(
                "6",
                "https://image.flaticon.com/icons/png/512/272/272186.png",
                "Dinner"
            ),
            Category(
                "7",
                "https://image.flaticon.com/icons/png/512/65/65667.png",
                "Wine"
            ),
            Category(
                "8",
                "https://image.flaticon.com/icons/png/512/599/599995.png",
                "Italian"
            )
        )
    }
}