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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.magentic_food.adapter.bevarageAdapter
import com.example.magentic_food.database.AppDB
import com.example.magentic_food.modelFactory.BevarageModelFactory
import com.example.magentic_food.modelFactory.BevarageRespository
import com.example.magentic_food.viewModel.BevarageViewModel

class BeverageFragment : Fragment(R.layout.beveragemenu) {

    private lateinit var _bevarageViewModel: BevarageViewModel
    private lateinit var _bevarageAdapter: bevarageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Your initialization code here
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnBevarageListCart = view.findViewById<Button>(R.id.bevarageViewCart)
        btnBevarageListCart.setOnClickListener {
            val intent = Intent(requireContext(), CartActivity::class.java)
            startActivity(intent)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.beveragemenu, container, false)

        val bevarageRecyclerView = view.findViewById<RecyclerView>(R.id.bevarage_Item_List)
        _bevarageAdapter = bevarageAdapter(requireContext())
        bevarageRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        bevarageRecyclerView.adapter = _bevarageAdapter

        // Initialize the ViewModel
        val beavargeDao = AppDB.getDatabase(requireContext()).beverageDao()
        val bevarageRepository = BevarageRespository(beavargeDao)
        _bevarageViewModel = ViewModelProvider(this, BevarageModelFactory(bevarageRepository))
            .get(BevarageViewModel::class.java)

        // Observe the LiveData from the ViewModel
        _bevarageViewModel.allBevarageInfo.observe(viewLifecycleOwner, Observer { bevarageList ->
            _bevarageAdapter.submitList(bevarageList)
        })

        return view
    }

}