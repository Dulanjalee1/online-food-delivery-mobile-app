package com.example.magentic_food.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.magentic_food.entity.Food

@Dao
interface FoodDao {
    @Insert
    suspend fun insert(food: Food)

    @Update
    suspend fun update(food: Food)

    @Delete
    suspend fun delete(food: Food)

    @Query("SELECT * FROM Food")
    suspend fun getAllFood(): List<Food>

    @Query("SELECT * FROM Food WHERE id = :foodId")
    suspend fun getFoodById(foodId: Long): Food
}