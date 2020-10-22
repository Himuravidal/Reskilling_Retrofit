package com.example.reskilling

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.reskilling.viewModel.SuperHeroesViewModel
import kotlinx.android.synthetic.main.fragment_second.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private val mViewmodel : SuperHeroesViewModel by activityViewModels()
    private var superHeroeID = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            superHeroeID = it.getInt("id")
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewmodel.getSuperHeroesByID(superHeroeID).observe(viewLifecycleOwner, Observer {
            if (it != null) {
                Glide.with(this).load(it.imageLg).into(detailImg)
                nameDetail.text = it.name
                alterEgo.text = it.alterEgos
                height.text = it.height.toString()
                switch1.isChecked = it.favorite
                Log.d("OBSEVADO", it.toString())
            }
        })

        switch1.setOnCheckedChangeListener { compoundButton, b ->
            mViewmodel.updateFav(superHeroeID, switch1.isChecked)
        }
    }
}