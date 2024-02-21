package com.example.magentic_food.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.magentic_food.DAO.BeverageDao
import com.example.magentic_food.DAO.CartDao
import com.example.magentic_food.DAO.FoodDao
import com.example.magentic_food.entity.Beverage
import com.example.magentic_food.entity.CartItem
import com.example.magentic_food.entity.Food

@Database(entities = [Food::class, Beverage::class, CartItem::class], version = 1,exportSchema = false)
abstract class AppDB : RoomDatabase() {
    abstract fun foodDao(): FoodDao
    abstract fun beverageDao(): BeverageDao
    abstract fun cartDao(): CartDao

    companion object {
        @Volatile
        private var INSTANCE: AppDB? = null

        fun getDatabase(context: Context): AppDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDB::class.java,
                    "shopping_cart"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}