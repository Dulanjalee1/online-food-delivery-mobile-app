package com.example.magentic_food.modelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.magentic_food.DAO.CartDao
import com.example.magentic_food.entity.CartItem
import com.example.magentic_food.viewModel.CartItemViewModel

class CartItemRespository(private  val cartDao: CartDao) {
    suspend fun getAllCartInfo(): List<CartItem> {
        return cartDao.getAllCartItems()
    }
}

class CartItemModelFactory(private val cartItemRespository: CartItemRespository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CartItemViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return CartItemViewModel(cartItemRespository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}