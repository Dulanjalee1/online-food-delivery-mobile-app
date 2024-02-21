package com.example.magentic_food.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Beverage(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val beverageName: String,
    val price: Double,
    val ImageName: String
)