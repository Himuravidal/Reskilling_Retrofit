package com.example.reskilling.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.reskilling.model.SuperHeroesRepository
import com.example.reskilling.model.local.SuperHeroesDatabase
import com.example.reskilling.model.local.SuperHeroesEntity

class SuperHeroesViewModel(application: Application) :AndroidViewModel(application){

    private val mRepository : SuperHeroesRepository
    val liveDataFromLocal : LiveData<List<SuperHeroesEntity>>

    init {
        val superHeroesDao = SuperHeroesDatabase.getDatabase(application).superHeroesDao()
        mRepository = SuperHeroesRepository(superHeroesDao)
        //mRepository.getDataFromServer()
        mRepository.getDataFromServerWithCorutines()
        liveDataFromLocal = mRepository.allSuperHeroesLiveData
    }


}