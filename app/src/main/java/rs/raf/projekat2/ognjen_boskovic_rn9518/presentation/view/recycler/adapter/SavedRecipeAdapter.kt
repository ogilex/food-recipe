package rs.raf.projekat2.ognjen_boskovic_rn9518.presentation.view.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.projekat2.ognjen_boskovic_rn9518.data.models.local.Recipe
import rs.raf.projekat2.ognjen_boskovic_rn9518.databinding.SavedRecipeItemBinding
import rs.raf.projekat2.ognjen_boskovic_rn9518.presentation.view.recycler.diff.SavedRecipeDiffItemCallBack
import rs.raf.projekat2.ognjen_boskovic_rn9518.presentation.view.recycler.viewholder.SavedRecipeViewHolder

class SavedRecipeAdapter(
    savedRecipeDiffItemCallBack: SavedRecipeDiffItemCallBack,
    private val detailsAction: (Recipe) -> Unit
) : ListAdapter<Recipe, SavedRecipeViewHolder>(savedRecipeDiffItemCallBack){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedRecipeViewHolder {
        val itemBinding = SavedRecipeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SavedRecipeViewHolder(itemBinding){
            detailsAction(getItem(it))
        }
    }

    override fun onBindViewHolder(holder: SavedRecipeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}