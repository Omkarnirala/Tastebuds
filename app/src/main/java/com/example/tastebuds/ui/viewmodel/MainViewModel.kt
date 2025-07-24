package com.example.tastebuds.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tastebuds.data.model.IngredientItem
import com.example.tastebuds.data.model.meals
import com.example.tastebuds.data.repo.MainRepo
import com.example.tastebuds.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepo: MainRepo
) : ViewModel() {

    private val _menuList = MutableLiveData<Resource<meals?>>()
    val userList: LiveData<Resource<meals?>>
        get() = _menuList

    val meals: ArrayList<meals.Meal?> = arrayListOf()

    fun getUserList() = viewModelScope.launch {
        _menuList.postValue(Resource.loading(null))
        mainRepo.getMealsList().let {
            if (it.isSuccessful) {
                meals.addAll(it.body()?.meals ?: arrayListOf())
                _menuList.postValue(Resource.success(it.body()))
                Log.d("Omkar", "getUserList: ${it.body()?.meals?.size}")
            } else {
                Log.d("Omkar", "getUserList: ${it.errorBody()?.string().toString().isEmpty()} ")
                if (it.errorBody()?.string().toString().isEmpty()) {
                    _menuList.postValue(Resource.errorBody(null, "Internet Issue"))
                } else {
                    _menuList.postValue(Resource.errorBody(null, it.errorBody()?.string().toString()))
                }
            }
        }
    }
}