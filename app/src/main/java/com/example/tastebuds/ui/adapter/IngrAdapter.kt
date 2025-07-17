package com.example.tastebuds.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.request.CachePolicy
import com.example.tastebuds.R
import com.example.tastebuds.data.model.IngredientItem
import com.example.tastebuds.databinding.RvIngredientsListBinding

class IngrAdapter(
    private val items: List<IngredientItem>?
) : RecyclerView.Adapter<IngrAdapter.IngredientViewHolder>() {

    inner class IngredientViewHolder(val binding: RvIngredientsListBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        val binding = RvIngredientsListBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return IngredientViewHolder(binding)
    }

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        val item = items?.get(position)
        holder.binding.ivLogo.load(item?.imageUrl) {
            crossfade(true)
            placeholder(R.drawable.no_image)
            error(R.drawable.no_image)
            memoryCachePolicy(CachePolicy.ENABLED)
            diskCachePolicy(CachePolicy.ENABLED)
            networkCachePolicy(CachePolicy.ENABLED)
        }
        holder.binding.tvIngredient.text = item?.name
    }

    override fun getItemCount(): Int = items?.size ?: 0
}
