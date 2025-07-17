package com.example.tastebuds.ui.view

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import coil.load
import coil.request.CachePolicy
import com.example.tastebuds.R
import com.example.tastebuds.data.model.IngredientItem
import com.example.tastebuds.databinding.ActivityMealInfoBinding
import com.example.tastebuds.ui.adapter.IngredientsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MealInfo : AppCompatActivity() {

    private val mTag = "MealInfo"
    private lateinit var binding: ActivityMealInfoBinding
    private lateinit var adapter: IngredientsAdapter
    private var isExpanded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMealInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        val title = intent.getStringExtra("Title")
        val description = intent.getStringExtra("Description")
        val youtubeLink = intent.getStringExtra("Youtube_Link")
        val imageSrc = intent.getStringExtra("Image_Src")
        val ingredients = intent.getParcelableArrayListExtra<IngredientItem>("ingredients") ?: emptyList()

        binding.ivMeal.load(imageSrc) {
            crossfade(true)
            placeholder(R.drawable.no_image)
            error(R.drawable.no_image)
            memoryCachePolicy(CachePolicy.ENABLED)
            diskCachePolicy(CachePolicy.ENABLED)
            networkCachePolicy(CachePolicy.ENABLED)
        }

        binding.tvTitle.text = title
        setupExpandableText(description, binding.tvDescription)
        binding.tvItemCount.text = buildString {
            append(ingredients.size)
            append(" Item")
        }

        binding.rvIngredients.setHasFixedSize(true)
        adapter = IngredientsAdapter(ingredients)
        binding.rvIngredients.adapter = adapter

        binding.btnYoutube.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(youtubeLink))
            startActivity(intent)
        }

    }

    fun setupExpandableText(fullText: String?, textView: TextView) {
        val actualText = fullText ?: "Instructions not available"
        val maxPreviewLength = 60

        if (actualText.length > maxPreviewLength) {
            textView.movementMethod = LinkMovementMethod.getInstance()

            val spannable: SpannableString
            if (isExpanded) {
                val prefix = "$actualText  "
                val suffix = "View Less"
                val finalString = prefix + suffix

                spannable = SpannableString(finalString)
                spannable.setSpan(object : ClickableSpan() {
                    override fun onClick(widget: View) {
                        isExpanded = false
                        setupExpandableText(fullText, textView)
                    }

                    override fun updateDrawState(ds: TextPaint) {
                        ds.color = Color.parseColor("#5DBCC3")
                        ds.isUnderlineText = false
                    }
                }, prefix.length, finalString.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

            } else {
                val prefix = actualText.substring(0, maxPreviewLength) + "... "
                val suffix = "View More"
                val finalString = prefix + suffix

                spannable = SpannableString(finalString)
                spannable.setSpan(object : ClickableSpan() {
                    override fun onClick(widget: View) {
                        isExpanded = true
                        setupExpandableText(fullText, textView)
                    }

                    override fun updateDrawState(ds: TextPaint) {
                        ds.color = Color.parseColor("#5DBCC3")
                        ds.isUnderlineText = false
                    }
                }, prefix.length, finalString.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            }

            textView.text = spannable
        } else {
            textView.text = actualText
            textView.movementMethod = null
        }
    }

}