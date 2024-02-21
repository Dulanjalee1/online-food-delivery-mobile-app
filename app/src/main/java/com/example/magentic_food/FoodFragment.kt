package com.example.magentic_food

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.magentic_food.adapter.foodAdapter
import com.example.magentic_food.database.AppDB
import com.example.magentic_food.modelFactory.FoodRespository
import com.example.magentic_food.modelFactory.FoodViewModelFactory
import com.example.magentic_food.viewModel.FoodViewModel

class FoodFragment : Fragment(R.layout.foodmenu) {

    private lateinit var _foodViewModel: FoodViewModel
    private lateinit var _foodAdapter: foodAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Your initialization code here
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnFoodListCart = view.findViewById<Button>(R.id.BtnfoodListCart)
        btnFoodListCart.setOnClickListener {
            val intent = Intent(requireContext(), CartActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateView(_inflater : LayoutInflater, _container : ViewGroup?, savedInstanceState: Bundle?) : View? {
        val view = _inflater.inflate(R.layout.foodmenu, _container, false)

        val foodRecyclerView = view.findViewById<RecyclerView>(R.id.food_cycle_list)
        _foodAdapter = foodAdapter(requireContext())
        foodRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        foodRecyclerView.adapter = _foodAdapter

        // Initialize the ViewModel
        val foodDao = AppDB.getDatabase(requireContext()).foodDao()
        val foodRepository = FoodRespository(foodDao)
        _foodViewModel = ViewModelProvider(this, FoodViewModelFactory(foodRepository))
            .get(FoodViewModel::class.java)

        // Observe the LiveData from the ViewModel
        _foodViewModel.allFoodInfo.observe(viewLifecycleOwner, Observer { foodList ->
            _foodAdapter.submitList(foodList)
        })

        return view
    }

}