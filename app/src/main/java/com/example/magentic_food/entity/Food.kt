package com.example.magentic_food.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class Food(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val foodName: String,
    val price: Double,
    val imageName: String
)

