package rs.raf.projekat2.ognjen_boskovic_rn9518.presentation.view.recycler.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import rs.raf.projekat2.ognjen_boskovic_rn9518.data.models.local.Recipe
import rs.raf.projekat2.ognjen_boskovic_rn9518.databinding.RecipeItemBinding
import kotlin.math.roundToInt

class RecipeViewHolder (
    private val itemBinding: RecipeItemBinding,
    detailsAction: (Int) -> Unit
) : RecyclerView.ViewHolder(itemBinding.root){

    init {
        itemBinding.root.setOnClickListener{detailsAction(bindingAdapterPosition)}
    }

    fun bind(recipe: Recipe){
        Glide.with(itemBinding.root).load(recipe.imageUrl).into(itemBinding.recipeImage)
        itemBinding.recipeTitle.text = recipe.title
        itemBinding.recipePublisher.text = recipe.publisher
        itemBinding.recipeSocialRank.text = recipe.socialRank.toDouble().roundToInt().toString()
    }
}