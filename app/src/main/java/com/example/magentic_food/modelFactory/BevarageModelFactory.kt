package com.example.magentic_food.modelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.magentic_food.DAO.BeverageDao
import com.example.magentic_food.entity.Beverage
import com.example.magentic_food.viewModel.BevarageViewModel


class BevarageRespository(private  val beverageDao: BeverageDao) {
    suspend fun getAllBeverageInfo(): List<Beverage> {
        return beverageDao.getAllBeverages()
    }
}

class BevarageModelFactory(private val bevarageRespository: BevarageRespository) :  ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BevarageViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return BevarageViewModel(bevarageRespository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}