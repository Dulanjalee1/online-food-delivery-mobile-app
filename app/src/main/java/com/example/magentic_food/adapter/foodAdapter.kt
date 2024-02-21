package com.example.magentic_food.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.magentic_food.R
import com.example.magentic_food.database.AppDB
import com.example.magentic_food.entity.CartItem
import com.example.magentic_food.entity.Food
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class foodAdapter(private val context: Context) : ListAdapter<Food, FoodViewHodler>(foodDiffCallback()) {

    private val cartItemInfo = AppDB.getDatabase(context).cartDao();

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHodler {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fooditem,parent,false)
        return  FoodViewHodler(view)
    }

    override fun onBindViewHolder(holder: FoodViewHodler, position: Int) {
        val _food = getItem(position)
        holder.foodBind(_food)

        holder.addToCart.setOnClickListener {
            val item = CartItem(0,_food.id,_food.foodName,1,_food.price,_food.imageName)
            insertCartItem(item)
            Toast.makeText(context, _food.foodName+" added to the cart", Toast.LENGTH_SHORT).show()
        }
    }

    private fun insertCartItem(cartItem: CartItem) {
        // Use a CoroutineScope to perform database operations in a background thread
        CoroutineScope(Dispatchers.IO).launch {
            cartItemInfo.insert(cartItem)
        }
    }
}

class foodDiffCallback: DiffUtil.ItemCallback<Food>() {
    override fun areItemsTheSame(oldItem: Food, newItem: Food): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Food, newItem: Food): Boolean {
        return oldItem == newItem
    }

}

class FoodViewHodler(itemView: View) : RecyclerView.ViewHolder(itemView) {
    //get fooditem layout object
    private  val foodNameView : TextView = itemView.findViewById(R.id.foodName)
    private  val foodPriceView : TextView = itemView.findViewById(R.id.foodPrice)
    private  val foodImageView : ImageView = itemView.findViewById(R.id.foodImage)
    val  addToCart : Button = itemView.findViewById(R.id.addFoodCart)

    fun foodBind (_food:Food){
        foodNameView.text = _food.foodName
        foodPriceView.text = "Rs : ${_food.price}"
        val foodImageResourceId = getFoodImageResorceId(_food.imageName)
        println("resouce id "+foodImageResourceId)
        foodImageView.setImageResource(foodImageResourceId)
    }


    //get resource id from drawable folder according to image name
    private  fun getFoodImageResorceId(imageName:String): Int{
        return itemView.context.resources.getIdentifier(imageName,"drawable", itemView.context.packageName)
    }

}
