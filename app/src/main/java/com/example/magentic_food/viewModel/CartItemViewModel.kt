package com.example.magentic_food.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.magentic_food.entity.CartItem
import com.example.magentic_food.modelFactory.CartItemRespository
import kotlinx.coroutines.launch

class CartItemViewModel(private val cartItemRespository: CartItemRespository) : ViewModel() {
    val allCartInfo = MutableLiveData<List<CartItem>>()

    init {
        fetchAllCartInfo()
    }
    private fun fetchAllCartInfo() {
        viewModelScope.launch {
            val data = cartItemRespository.getAllCartInfo()
            allCartInfo.value = data
        }
    }
}