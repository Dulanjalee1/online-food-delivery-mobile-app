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
import com.example.magentic_food.entity.Beverage
import com.example.magentic_food.entity.CartItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class bevarageAdapter(private val context: Context) : ListAdapter<Beverage, BevarageViewHodler>(beverageDiffCallback()) {
    private val cartItemInfo = AppDB.getDatabase(context).cartDao();


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BevarageViewHodler {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.bevarageitem,parent,false)
        return  BevarageViewHodler(view)
    }

    override fun onBindViewHolder(holder: BevarageViewHodler, position: Int) {
        val _bevarage = getItem(position)
        holder.bevarageBind(_bevarage)

        holder.addToCart.setOnClickListener {
            val item = CartItem(0,_bevarage.id,_bevarage.beverageName,1,_bevarage.price,_bevarage.ImageName)
            insertCartItem(item)
            Toast.makeText(context, _bevarage.beverageName+" added to the cart", Toast.LENGTH_SHORT).show()
        }
    }

    private fun insertCartItem(cartItem: CartItem) {
        // Use a CoroutineScope to perform database operations in a background thread
        CoroutineScope(Dispatchers.IO).launch {
            cartItemInfo.insert(cartItem)
        }
    }
}

class beverageDiffCallback: DiffUtil.ItemCallback<Beverage>() {
    override fun areItemsTheSame(oldItem: Beverage, newItem: Beverage): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Beverage, newItem: Beverage): Boolean {
        return oldItem == newItem
    }

}

class BevarageViewHodler(itemView: View) : RecyclerView.ViewHolder(itemView) {
    //get fooditem layout object
    private  val bevarageNameView : TextView = itemView.findViewById(R.id.bevarageName)
    private  val bevaragePriceView : TextView = itemView.findViewById(R.id.bevaragePrice)
    private  val bevarageImageView : ImageView = itemView.findViewById(R.id.bevarageImage)
    val  addToCart : Button = itemView.findViewById(R.id.addBvarageCart)

    fun bevarageBind (_bevarage:Beverage){
        bevarageNameView.text = _bevarage.beverageName
        bevaragePriceView.text = "Rs : ${_bevarage.price}"
        val bevarageImageResourceId = getBevarageImageResorceId(_bevarage.ImageName)
        bevarageImageView.setImageResource(bevarageImageResourceId)

    }


    //get resource id from drawable folder according to image name
    private  fun getBevarageImageResorceId(imageName:String): Int{
        return itemView.context.resources.getIdentifier(imageName,"drawable", itemView.context.packageName)
    }

}
