package com.example.tastebuds.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.request.CachePolicy
import com.example.tastebuds.R
import com.example.tastebuds.data.model.IngredientItem
import com.example.tastebuds.databinding.ItemIngredientBinding

class IngredientsAdapter(
    private val items: List<IngredientItem>?
) : RecyclerView.Adapter<IngredientsAdapter.IngredientViewHolder>() {

    inner class IngredientViewHolder(val binding: ItemIngredientBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemIngredientBinding.inflate(inflater, parent, false)
        return IngredientViewHolder(binding)
    }

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        val item = items?.get(position)
        Log.d("IngredientsAdapter", "onBindViewHolder: $item")
        holder.binding.apply {
            tvIngredientName.text = item?.name
            tvIngredientQty.text = item?.quantity
            ivIngredient.load(item?.imageUrl) {
                placeholder(R.drawable.no_image)
                error(R.drawable.no_image)
                crossfade(true)
                memoryCachePolicy(CachePolicy.ENABLED)
                diskCachePolicy(CachePolicy.ENABLED)
                networkCachePolicy(CachePolicy.ENABLED)
            }
        }
    }

    override fun getItemCount(): Int = items?.size ?: 0
}