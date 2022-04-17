package com.mfathoer.sharecipe.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mfathoer.sharecipe.databinding.ItemRecipeBinding
import com.mfathoer.sharecipe.domain.model.Recipe

class RecipesListAdapter : ListAdapter<Recipe, RecipesListAdapter.RecipeViewHolder>(DIFF_CALLBACK) {

    private lateinit var binding: ItemRecipeBinding

    var onItemRecipeClicked: ((Recipe) -> Unit)? = null

    inner class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Recipe) {
            binding.apply {
                recipe = item
                root.setOnClickListener { onItemRecipeClicked?.invoke(item) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        binding = ItemRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Recipe>() {
            override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
                return oldItem.id == newItem.id && oldItem.title == newItem.title
            }
        }
    }
}