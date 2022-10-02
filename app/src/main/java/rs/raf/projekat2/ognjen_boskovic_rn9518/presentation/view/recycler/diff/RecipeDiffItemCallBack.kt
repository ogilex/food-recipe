package rs.raf.projekat2.ognjen_boskovic_rn9518.presentation.view.recycler.diff

import androidx.recyclerview.widget.DiffUtil
import rs.raf.projekat2.ognjen_boskovic_rn9518.data.models.local.Recipe

class RecipeDiffItemCallBack : DiffUtil.ItemCallback<Recipe>() {

    override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
        return oldItem.imageUrl == newItem.imageUrl
                && oldItem.title == newItem.title
                && oldItem.socialRank == newItem.socialRank
                && oldItem.publisher == newItem.publisher
    }
}