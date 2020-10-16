package com.example.reskilling.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.reskilling.R
import com.example.reskilling.model.local.SuperHeroesEntity
import com.example.reskilling.viewModel.SuperHeroesViewModel
import kotlinx.android.synthetic.main.fragment_first.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() , SuperHeroesAdapter.PassTheData {

    lateinit var mViewModel : SuperHeroesViewModel
    lateinit var mAdapter: SuperHeroesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProvider(this).get(SuperHeroesViewModel::class.java)
        mAdapter = SuperHeroesAdapter(this)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = mRecycler
        recyclerView.adapter = mAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
        mViewModel.liveDataFromLocal.observe(viewLifecycleOwner, Observer {
            Log.d("FROMDB", it.toString())

        })

        mViewModel.allFavoritos.observe(viewLifecycleOwner, Observer {
            Log.d("FAVORiTES0", it.toString())
            mAdapter.updateAdapter(it)
        })
    }

    override fun passTheSuperHeroes(superHeroe: SuperHeroesEntity) {
        val bundle = Bundle()
        bundle.putInt("id", superHeroe.id)
        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
    }
}