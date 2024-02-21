package com.example.magentic_food.modelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.magentic_food.DAO.FoodDao
import com.example.magentic_food.entity.Food
import com.example.magentic_food.viewModel.FoodViewModel

class FoodRespository(private  val foodDao: FoodDao) {
    suspend fun getAllFoodInfo(): List<Food> {
        return foodDao.getAllFood()
    }
}

class FoodViewModelFactory(private val foodRespository: FoodRespository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FoodViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return FoodViewModel(foodRespository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}