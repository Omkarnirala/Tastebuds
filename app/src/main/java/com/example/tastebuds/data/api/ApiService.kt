package com.example.tastebuds.data.api

import com.example.tastebuds.data.model.meals
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("json/v2/1/randomselection.php")
    suspend fun fetchMealsList(): Response<meals>

}