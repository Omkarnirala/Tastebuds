package com.example.tastebuds.ui.adapter

import android.graphics.Color
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.request.CachePolicy
import com.example.tastebuds.R
import com.example.tastebuds.data.model.IngredientItem
import com.example.tastebuds.data.model.meals
import com.example.tastebuds.databinding.RvItemListBinding

class MealListAdapter(
    private val meals: List<meals.Meal?>?,
): RecyclerView.Adapter<MealListAdapter.MealViewHolder>() {

    private val expandedPositions = mutableSetOf<Int>()
    var ingredients = listOf<IngredientItem>()
    var mealClickListener: OnMealClickListener? = null

    interface OnMealClickListener {
        fun onMealClick(meal: meals.Meal, ingredients: List<IngredientItem>)
    }

    inner class MealViewHolder(val binding: RvItemListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val binding = RvItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MealViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        val meal = meals?.get(position)

        holder.binding.apply {

            tvTitle.text = meal?.strMeal ?: "N/A"
            val fullText = meal?.strInstructions ?: "Instructions not available"
            val isExpanded = expandedPositions.contains(position)

            val maxPreviewLength = 60

            if (fullText.length > maxPreviewLength) {
                tvSubTitle.movementMethod = LinkMovementMethod.getInstance()

                val spannable: SpannableString
                if (isExpanded) {
                    val prefix = "$fullText  "
                    val suffix = "View Less"
                    val finalString = prefix + suffix

                    spannable = SpannableString(finalString)
                    spannable.setSpan(
                        object : ClickableSpan() {
                            override fun onClick(widget: View) {
                                expandedPositions.remove(position)
                                notifyItemChanged(position)
                            }
                            override fun updateDrawState(ds: TextPaint) {
                                ds.color = Color.parseColor("#5DBCC3")
                                ds.isUnderlineText = false
                            }
                        },
                        prefix.length,
                        finalString.length,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                } else {

                    val prefix = fullText.substring(0, maxPreviewLength) + "... "
                    val suffix = "View More"
                    val finalString = prefix + suffix

                    spannable = SpannableString(finalString)
                    spannable.setSpan(
                        object : ClickableSpan() {
                            override fun onClick(widget: View) {
                                expandedPositions.add(position)
                                notifyItemChanged(position)
                            }
                            override fun updateDrawState(ds: TextPaint) {
                                ds.color = Color.parseColor("#5DBCC3")
                                ds.isUnderlineText = false
                            }
                        },
                        prefix.length,
                        finalString.length,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                }
                tvSubTitle.text = spannable

            } else {
                tvSubTitle.text = fullText
                tvSubTitle.movementMethod = null
            }

            ivLogo.load(meal?.strMealThumb) {
                crossfade(true)
                placeholder(R.drawable.no_image)
                error(R.drawable.no_image)
                memoryCachePolicy(CachePolicy.ENABLED)
                diskCachePolicy(CachePolicy.ENABLED)
                networkCachePolicy(CachePolicy.ENABLED)
            }

            ingredients = extractIngredients(meal)
            rvIngredients.apply {
                layoutManager = LinearLayoutManager(root.context, RecyclerView.HORIZONTAL, false)
                adapter = IngrAdapter(ingredients)
            }
        }

        holder.binding.rootLYT.setOnClickListener {
            meal?.let {
                Log.d("mealListAdapter", "Clicked: ${it.strArea}")
                mealClickListener?.onMealClick(it, ingredients)
            }
        }
    }

    override fun getItemCount(): Int = meals?.size ?: 0

    private fun extractIngredients(meal: meals.Meal?): List<IngredientItem> {
        val ingredients = mutableListOf<IngredientItem>()
        val baseImageUrl = "https://www.themealdb.com/images/ingredients/"

        if (meal == null) return ingredients

        for (i in 1..20) {
            val ingredient = meal::class.java.getDeclaredField("strIngredient$i").apply { isAccessible = true }.get(meal) as? String
            val measure = meal::class.java.getDeclaredField("strMeasure$i").apply { isAccessible = true }.get(meal) as? String

            if (!ingredient.isNullOrBlank() && ingredient != "null" && !measure.isNullOrBlank()) {
                val trimmedName = ingredient.trim()
                val imageUrl = "$baseImageUrl${trimmedName}.png"
                ingredients.add(IngredientItem(trimmedName, measure.trim(), imageUrl))
            }
        }
        return ingredients
    }
}
