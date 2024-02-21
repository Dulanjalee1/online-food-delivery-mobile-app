package com.example.magentic_food.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.magentic_food.entity.Food
import com.example.magentic_food.modelFactory.FoodRespository
import kotlinx.coroutines.launch

class FoodViewModel(private val foodRespository: FoodRespository) : ViewModel() {
    val allFoodInfo = MutableLiveData<List<Food>>()

    init {
        fetchAllFoodInfo()
    }
    private fun fetchAllFoodInfo() {
        viewModelScope.launch {
            val data = foodRespository.getAllFoodInfo()
            allFoodInfo.value = data
        }
    }
}