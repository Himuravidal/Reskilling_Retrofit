package com.example.reskilling.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
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

    private val mViewModel : SuperHeroesViewModel by activityViewModels()
    private lateinit var mAdapter: SuperHeroesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

       val recy = view.findViewById<RecyclerView>(R.id.mRecycler)
        mRecycler.adapter = mAdapter
        mRecycler.layoutManager = LinearLayoutManager(context)
        mRecycler.setHasFixedSize(true)
        mViewModel.liveDataFromLocal.observe(viewLifecycleOwner, Observer {
            Log.d("FROMDB", it.toString())
            mAdapter.updateAdapter(it)
        })

        mViewModel.allFavoritos.observe(viewLifecycleOwner, Observer {
            Log.d("FAVORiTES0", it.toString())
        })
    }

    override fun passTheSuperHeroes(superHeroe: SuperHeroesEntity) {
        val bundle = Bundle()
        bundle.putInt("id", superHeroe.id)
        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
    }
}