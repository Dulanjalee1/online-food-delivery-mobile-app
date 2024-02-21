package com.example.magentic_food

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.magentic_food.DAO.BeverageDao
import com.example.magentic_food.DAO.FoodDao
import com.example.magentic_food.database.AppDB
import com.example.magentic_food.entity.Beverage
import com.example.magentic_food.entity.Food
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    // Declare variables for Room Database and DAOs
    private lateinit var foodDao: FoodDao
    private lateinit var beverageDao: BeverageDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val shopnow = findViewById<Button>(R.id.button2)
        shopnow.setOnClickListener {
            val intent = Intent(this, Menumain::class.java)
            startActivity(intent)
        }

        // Initialize your Room Database and DAOs
        val DB = AppDB.getDatabase(this.applicationContext)
        foodDao = DB.foodDao()
        beverageDao = DB.beverageDao()

        CoroutineScope(Dispatchers.IO).launch {
            addingDummyDataToDB()
        }

    }

    private suspend fun addingDummyDataToDB() {
        //adding dummy data to food
        val foodData = listOf<Food>(
            Food(0,"Pizza", 2500.0, "pizza"),
            Food(0,"Pasta", 1500.0, "pasta"),
            Food(0,"Burgers", 1000.0, "burgers"),
            Food(0,"Tacos", 1200.0, "tacos"),
            Food(0,"Sandwitch", 200.0, "sandwitch"),
            Food(0,"FrenchFries", 400.0, "frenchfries"),
            Food(0,"Waffeles", 350.0, "waffeles")
        )


        //adding dummy data to bevarage
        val beverageData = listOf<Beverage>(
            Beverage(0,"LimeMojito",450.0,"limemojito"),
            Beverage(0,"chocolate MilkShake",900.0,"chocolate"),
            Beverage(0,"Strawberry IcedTea",450.0,"strawberry"),
            Beverage(0,"blueberry Mojito",700.0,"blueberry"),
            Beverage(0,"HotChocolate",400.0,"hotchocolate"),
            Beverage(0,"IceCoffee",400.0,"icecoffee"),
            Beverage(0,"Cappuccino",450.0,"cappuccino")
        )

        // Insert the data into the database
        foodData.forEach { foodDao.insert(it) }
        beverageData.forEach { beverageDao.insert(it) }
    }
}