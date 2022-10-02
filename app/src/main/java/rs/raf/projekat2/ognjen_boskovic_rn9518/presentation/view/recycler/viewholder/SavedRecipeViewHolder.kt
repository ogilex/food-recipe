package rs.raf.projekat2.ognjen_boskovic_rn9518.presentation.view.recycler.viewholder

import androidx.recyclerview.widget.RecyclerView
import rs.raf.projekat2.ognjen_boskovic_rn9518.data.models.local.Recipe
import rs.raf.projekat2.ognjen_boskovic_rn9518.databinding.SavedRecipeItemBinding
import java.text.SimpleDateFormat
import java.util.*

class SavedRecipeViewHolder(
    private val itemBinding: SavedRecipeItemBinding,
    detailsAction: (Int) -> Unit
) : RecyclerView.ViewHolder(itemBinding.root) {

    init {
        itemBinding.root.setOnClickListener{ detailsAction(bindingAdapterPosition) }
    }

    fun bind(recipe: Recipe){
        itemBinding.savedRecipeTitle.text = recipe.title
        itemBinding.savedRecipeCategory.text = recipe.category
        val formatter = SimpleDateFormat("dd-MM-yyyy")
        val date: Date = recipe.date
        itemBinding.savedRecipeDate.text = formatter.format(date)
    }
}