package com.example.reskilling.model.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface SuperHeroesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE )
    suspend fun insertAllSuperHeroes(mList: List<SuperHeroesEntity>)

    @Query("SELECT * FROM super_heroes_table")
    fun showAllSuperHeroes(): LiveData<List<SuperHeroesEntity>>

    @Query("SELECT * FROM super_heroes_table WHERE id =:mId")
    fun showOnSuperHeroesByID(mId : Int): LiveData<SuperHeroesEntity>

    @Query("UPDATE super_heroes_table SET favorite =:fav WHERE id =:mId")
    fun updateFav(mId: Int, fav: Boolean)

    @Query("SELECT * FROM super_heroes_table WHERE favorite = 1")
    fun allFavorites(): LiveData<List<SuperHeroesEntity>>
}