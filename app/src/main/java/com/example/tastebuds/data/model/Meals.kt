package com.example.tastebuds.data.model

import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class meals(
    @SerializedName("meals")
    val meals: List<Meal?>?
) {
    @Keep
    data class Meal(
        @SerializedName("dateModified")
        val dateModified: String?, // null
        @SerializedName("idMeal")
        val idMeal: String?, // 52954
        @SerializedName("strArea")
        val strArea: String?, // Chinese
        @SerializedName("strCategory")
        val strCategory: String?, // Pork
        @SerializedName("strCreativeCommonsConfirmed")
        val strCreativeCommonsConfirmed: String?, // null
        @SerializedName("strImageSource")
        val strImageSource: String?, // null
        @SerializedName("strIngredient1")
        val strIngredient1: String?, // Mushrooms
        @SerializedName("strIngredient10")
        val strIngredient10: String?, // Hotsauce
        @SerializedName("strIngredient11")
        val strIngredient11: String?, // Vinegar
        @SerializedName("strIngredient12")
        val strIngredient12: String?, // Soy Sauce
        @SerializedName("strIngredient13")
        val strIngredient13: String?, // Cornstarch
        @SerializedName("strIngredient14")
        val strIngredient14: String?, // Water
        @SerializedName("strIngredient15")
        val strIngredient15: String?, // Spring Onions
        @SerializedName("strIngredient16")
        val strIngredient16: String?,
        @SerializedName("strIngredient17")
        val strIngredient17: String?,
        @SerializedName("strIngredient18")
        val strIngredient18: String?,
        @SerializedName("strIngredient19")
        val strIngredient19: String?,
        @SerializedName("strIngredient2")
        val strIngredient2: String?, // Wood Ear Mushrooms
        @SerializedName("strIngredient20")
        val strIngredient20: String?,
        @SerializedName("strIngredient3")
        val strIngredient3: String?, // Tofu
        @SerializedName("strIngredient4")
        val strIngredient4: String?, // Pork
        @SerializedName("strIngredient5")
        val strIngredient5: String?, // Chicken Stock
        @SerializedName("strIngredient6")
        val strIngredient6: String?, // Salt
        @SerializedName("strIngredient7")
        val strIngredient7: String?, // Sugar
        @SerializedName("strIngredient8")
        val strIngredient8: String?, // Sesame Seed Oil
        @SerializedName("strIngredient9")
        val strIngredient9: String?, // Pepper
        @SerializedName("strInstructions")
        val strInstructions: String?, // STEP 1 - MAKING THE SOUPIn a wok add chicken broth and wait for it to boil.Next add salt, sugar, sesame seed oil, white pepper, hot pepper sauce, vinegar and soy sauce and stir for few seconds.Add Tofu, mushrooms, black wood ear mushrooms to the wok.To thicken the sauce, whisk together 1 Tablespoon of cornstarch and 2 Tablespoon of water in a bowl and slowly add to your soup until it's the right thickness.Next add 1 egg slightly beaten with a knife or fork and add it to the soup and stir for 8 secondsServe the soup in a bowl and add the bbq pork and sliced green onions on top.
        @SerializedName("strMeal")
        val strMeal: String?, // Hot and Sour Soup
        @SerializedName("strMealAlternate")
        val strMealAlternate: String?, // null
        @SerializedName("strMealThumb")
        val strMealThumb: String?, // https://www.themealdb.com/images/media/meals/1529445893.jpg
        @SerializedName("strMeasure1")
        val strMeasure1: String?, // 1/3 cup
        @SerializedName("strMeasure10")
        val strMeasure10: String?, // 1/2 tsp
        @SerializedName("strMeasure11")
        val strMeasure11: String?, // 1-½ cups
        @SerializedName("strMeasure12")
        val strMeasure12: String?, // 1 tsp 
        @SerializedName("strMeasure13")
        val strMeasure13: String?, // 1 tbs
        @SerializedName("strMeasure14")
        val strMeasure14: String?, // 2 tbs
        @SerializedName("strMeasure15")
        val strMeasure15: String?, // 1/4 cup
        @SerializedName("strMeasure16")
        val strMeasure16: String?,
        @SerializedName("strMeasure17")
        val strMeasure17: String?,
        @SerializedName("strMeasure18")
        val strMeasure18: String?,
        @SerializedName("strMeasure19")
        val strMeasure19: String?,
        @SerializedName("strMeasure2")
        val strMeasure2: String?, // 1/3 cup
        @SerializedName("strMeasure20")
        val strMeasure20: String?,
        @SerializedName("strMeasure3")
        val strMeasure3: String?, // 2/3 Cup
        @SerializedName("strMeasure4")
        val strMeasure4: String?, // 1/2 cup 
        @SerializedName("strMeasure5")
        val strMeasure5: String?, // 2-1/2 cups
        @SerializedName("strMeasure6")
        val strMeasure6: String?, // 1/2 tsp
        @SerializedName("strMeasure7")
        val strMeasure7: String?, // 1/4 tsp
        @SerializedName("strMeasure8")
        val strMeasure8: String?, // 1 tsp 
        @SerializedName("strMeasure9")
        val strMeasure9: String?, // 1/4 tsp
        @SerializedName("strSource")
        val strSource: String?, // https://sueandgambo.com/pages/chinese-hot-and-sour-soup
        @SerializedName("strTags")
        val strTags: String?, // Soup
        @SerializedName("strYoutube")
        val strYoutube: String? // https://www.youtube.com/watch?v=KgV9Zq3aSTo
    )
}