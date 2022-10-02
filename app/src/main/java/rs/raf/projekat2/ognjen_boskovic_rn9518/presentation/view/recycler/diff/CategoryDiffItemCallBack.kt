package rs.raf.projekat2.ognjen_boskovic_rn9518.presentation.view.recycler.diff

import androidx.recyclerview.widget.DiffUtil
import rs.raf.projekat2.ognjen_boskovic_rn9518.data.models.local.Category

class CategoryDiffItemCallBack : DiffUtil.ItemCallback<Category>() {

    override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem.img == newItem.img
                && oldItem.title == newItem.title
    }
}