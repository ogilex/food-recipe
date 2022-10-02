package rs.raf.projekat2.ognjen_boskovic_rn9518.presentation.view.recycler.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import rs.raf.projekat2.ognjen_boskovic_rn9518.data.models.local.Category
import rs.raf.projekat2.ognjen_boskovic_rn9518.databinding.CategoryItemBinding

class CategoryViewHolder(
    private val itemBinding: CategoryItemBinding,
    searchAction: (Int) -> Unit) : RecyclerView.ViewHolder(itemBinding.root) {

    init {
        itemBinding.root.setOnClickListener{ searchAction(bindingAdapterPosition)}
    }

    fun bind(category: Category) {
        Glide.with(itemBinding.root).load(category.img).into(itemBinding.categoryImage)
        itemBinding.categoryTitle.text = category.title
    }
}