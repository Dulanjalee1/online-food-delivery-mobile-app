package com.example.magentic_food.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.magentic_food.entity.Beverage

@Dao
interface BeverageDao {
    @Insert
    suspend fun insert(beverage: Beverage)

    @Update
    suspend fun update(beverage: Beverage)

    @Delete
    suspend fun delete(beverage: Beverage)

    @Query("SELECT * FROM Beverage")
    suspend fun getAllBeverages(): List<Beverage>

    @Query("SELECT * FROM Beverage WHERE id = :beverageId")
    suspend fun getBeverageById(beverageId: Long): Beverage
}