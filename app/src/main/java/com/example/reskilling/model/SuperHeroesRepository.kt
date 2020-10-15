package com.example.reskilling.model

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.reskilling.model.local.SuperHeroesDao
import com.example.reskilling.model.local.SuperHeroesEntity
import com.example.reskilling.model.network.RetrofitClient
import com.example.reskilling.model.pojos.SuperHeroe
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SuperHeroesRepository(private val superHeroesDao: SuperHeroesDao) {

    private val retroService = RetrofitClient.getRetrofitClient()
    // Este trae toda la tabla superHeroes entity
    val allSuperHeroesLiveData = superHeroesDao.showAllSuperHeroes()

    //La vieja confiable
    fun getDataFromServer() {
        val call = retroService.fetchAllSuperHeroes()
        call.enqueue(object : Callback<List<SuperHeroe>> {
            override fun onResponse(
                call: Call<List<SuperHeroe>>,
                response: Response<List<SuperHeroe>>
            ) {
                    when(response.code()) {
                        in 200..299 -> CoroutineScope(Dispatchers.IO).launch {
                                Log.d("RESPONSEOK", response.body().toString())
                                response.body()?.let {
                                    superHeroesDao.insertAllSuperHeroes(convert(it))
                                }
                            }
                        in 300..599 -> Log.d("RESPONSE_300-", response.body().toString())
                        else -> Log.d("ERROR", response.errorBody().toString())
                    }
            }
            override fun onFailure(call: Call<List<SuperHeroe>>, t: Throwable) {
               Log.e("ERROR", t.message.toString())
            }
        })
    }

    // Este ejemplo es con Corutinas
    fun getDataFromServerWithCorutines() = CoroutineScope(Dispatchers.IO).launch {
        val service = kotlin.runCatching { retroService.fetchAllSuperHeroesWithCoroutines() }
        service.onSuccess {
            when(it.code()) {
                in 200..299 -> it.body()?.let {
                        superHeroesDao.insertAllSuperHeroes(convert(it))
                }
                in 300..599 -> Log.d("RESPONSE_300-", it.body().toString())
                else -> Log.d("ERROR", it.errorBody().toString())
            }
        }
        service.onFailure {
            Log.e("ERROR", it.message.toString())
        }
    }

    fun convert(listFromNetwork: List<SuperHeroe>): List<SuperHeroesEntity> {
        val listMutable = mutableListOf<SuperHeroesEntity>()
        listFromNetwork.map {
            listMutable.add( SuperHeroesEntity(it.id,
                it.images.xs,
                it.images.lg,
                it.name,
                it.biography.alterEgos,
                it.appearance.height))
        }
        return listMutable
    }
}