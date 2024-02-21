package com.example.magentic_food.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.magentic_food.entity.Beverage
import com.example.magentic_food.modelFactory.BevarageRespository
import kotlinx.coroutines.launch

class BevarageViewModel(private val bevargeRespository: BevarageRespository) : ViewModel() {
    val allBevarageInfo = MutableLiveData<List<Beverage>>()

    init {
        fetchAllFoodInfo()
    }
    private fun fetchAllFoodInfo() {
        viewModelScope.launch {
            val data = bevargeRespository.getAllBeverageInfo()
            allBevarageInfo.value = data
        }
    }
}