package rs.raf.projekat2.ognjen_boskovic_rn9518.presentation.activities

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.projekat2.ognjen_boskovic_rn9518.R
import rs.raf.projekat2.ognjen_boskovic_rn9518.data.models.local.Recipe
import rs.raf.projekat2.ognjen_boskovic_rn9518.databinding.ActivitySaveRecipeBinding
import rs.raf.projekat2.ognjen_boskovic_rn9518.presentation.contract.RecipeContract
import rs.raf.projekat2.ognjen_boskovic_rn9518.presentation.viewmodel.RecipeViewModel
import java.text.SimpleDateFormat
import java.util.*

class SaveRecipeActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySaveRecipeBinding
    private val recipeViewModel: RecipeContract.ViewModel by viewModel<RecipeViewModel>()
    private lateinit var recipe: Recipe
    private var selectedYear = Calendar.getInstance().get(Calendar.YEAR)
    private var selectedMonth = Calendar.getInstance().get(Calendar.MONTH)
    private var selectedDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
    private lateinit var selectedDate: Date

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recipe = intent.getSerializableExtra(MainActivity.RECIPE_KEY) as Recipe
        binding = ActivitySaveRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()

    }

    private fun init(){
        initView()
        initListeners()
    }

    private fun initView(){
        binding.recipeTitle.text = recipe.title
        Glide.with(binding.root).load(recipe.imageUrl).into(binding.recipeImage)
        val formatter = SimpleDateFormat("dd-MM-yyyy")
        val date = Date()
        selectedDate = date
        binding.dateBtn.text = formatter.format(date)

        val spinner = binding.categorySpinner
        ArrayAdapter.createFromResource(
            this,
            R.array.categories_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }


    }

    private fun initListeners(){
        binding.addToMenuBtn.setOnClickListener(){
            val category: String = binding.categorySpinner.selectedItem.toString()
            recipeViewModel.saveRecipe(recipe, category, selectedDate)
            Toast.makeText(this, "Recipe saved successfully", Toast.LENGTH_SHORT).show()
        }

        binding.dateBtn.setOnClickListener{

            val currDate = Calendar.getInstance()
            var year = currDate.get(Calendar.YEAR)
            var month = currDate.get(Calendar.MONTH)
            var day = currDate.get(Calendar.DAY_OF_MONTH)

            if(binding.dateBtn.text.isNotEmpty()){
                year = this.selectedYear
                month = this.selectedMonth
                day = this.selectedDay
            }
            val listener = DatePickerDialog.OnDateSetListener{ datePicker, selectedYear, selectedMonth, selectedDay ->
                this.selectedYear = selectedYear
                this.selectedMonth = selectedMonth
                this.selectedDay = selectedDay
                binding.dateBtn.text = "${selectedDay}-${selectedMonth+1}-${selectedYear}"
                selectedDate = SimpleDateFormat("dd-MM-yyyy").parse(binding.dateBtn.text.toString())
            }

            val datePicker = DatePickerDialog(this, listener, year, month, day)
            datePicker.show()
        }
    }
}