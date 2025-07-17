package com.example.tastebuds.ui.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tastebuds.R
import com.example.tastebuds.data.model.IngredientItem
import com.example.tastebuds.data.model.meals
import com.example.tastebuds.databinding.ActivityMainBinding
import com.example.tastebuds.ui.adapter.MealListAdapter
import com.example.tastebuds.ui.viewmodel.MainViewModel
import com.example.tastebuds.utils.Status
import com.google.gson.JsonSyntaxException
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mTag = "MainActivity"
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MealListAdapter
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initComponent()
        viewModelObserve()
    }

    private fun initComponent() {
        mainViewModel.getUserList()
    }

    private fun viewModelObserve() {
        mainViewModel.userList.observe(this) {
            when(it.status) {
                Status.SUCCESS -> {
                    binding.ProgressBar.visibility = View.GONE
                    Log.d("TAG", "viewModelObserve: ${it.data}")
                    adapter = MealListAdapter(it.data?.meals)
                    binding.rvMeals.adapter = adapter

                    adapter.mealClickListener = object : MealListAdapter.OnMealClickListener {
                        override fun onMealClick(meal: meals.Meal, ingredients: List<IngredientItem>) {
                            val intent = Intent(this@MainActivity, MealInfo::class.java)
                            intent.putExtra("Title", meal.strMeal)
                            intent.putExtra("Description", meal.strInstructions)
                            intent.putExtra("Youtube_Link", meal.strYoutube)
                            intent.putExtra("Image_Src", meal.strMealThumb)
                            intent.putParcelableArrayListExtra("ingredients", ArrayList(ingredients))
                            startActivity(intent)
                        }
                    }
                }

                Status.ERROR -> {
                    binding.ProgressBar.visibility = View.GONE
                    Log.d(mTag, "viewModelObserve: ${it.message}")
                    try {
                        val error: String = it.message.toString()
                        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
                    } catch (e: JsonSyntaxException) {
                        e.printStackTrace()
                    }
                }

                Status.LOADING -> {
                    binding.ProgressBar.visibility = View.VISIBLE
                }
            }
        }
    }
}