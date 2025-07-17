package com.example.tastebuds.data.repo

import com.example.tastebuds.data.api.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepo @Inject constructor(
    private val apiServices: ApiService,
) {

    suspend fun getMealsList() = apiServices.fetchMealsList()

}