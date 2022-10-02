package rs.raf.projekat2.ognjen_boskovic_rn9518.presentation.view.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.projekat2.ognjen_boskovic_rn9518.data.models.local.Category
import rs.raf.projekat2.ognjen_boskovic_rn9518.databinding.CategoryItemBinding
import rs.raf.projekat2.ognjen_boskovic_rn9518.presentation.view.recycler.diff.CategoryDiffItemCallBack
import rs.raf.projekat2.ognjen_boskovic_rn9518.presentation.view.recycler.viewholder.CategoryViewHolder

class CategoryAdapter (
    categoryDiffItemCallBack: CategoryDiffItemCallBack,
    private val searchAction: (Category) -> Unit
) : ListAdapter<Category, CategoryViewHolder>(categoryDiffItemCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val itemBinding = CategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(itemBinding){
            searchAction(getItem(it))
        }
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}