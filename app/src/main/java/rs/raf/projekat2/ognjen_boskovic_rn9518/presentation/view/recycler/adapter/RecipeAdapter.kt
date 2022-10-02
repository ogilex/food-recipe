package rs.raf.projekat2.ognjen_boskovic_rn9518.presentation.view.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.projekat2.ognjen_boskovic_rn9518.data.models.local.Recipe
import rs.raf.projekat2.ognjen_boskovic_rn9518.databinding.RecipeItemBinding
import rs.raf.projekat2.ognjen_boskovic_rn9518.presentation.view.recycler.diff.RecipeDiffItemCallBack
import rs.raf.projekat2.ognjen_boskovic_rn9518.presentation.view.recycler.viewholder.RecipeViewHolder

class RecipeAdapter (
    recipeDiffItemCallBack: RecipeDiffItemCallBack,
    private val detailsAction: (Recipe) -> Unit
) : ListAdapter<Recipe, RecipeViewHolder>(recipeDiffItemCallBack){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val itemBinding = RecipeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeViewHolder(itemBinding){
            detailsAction(getItem(it))
        }
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}