package com.example.reskilling.local

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.reskilling.model.local.SuperHeroesDao
import com.example.reskilling.model.local.SuperHeroesDatabase
import com.example.reskilling.model.local.SuperHeroesEntity
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TestDao {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var mSuperHeroesDao: SuperHeroesDao
    private lateinit var db : SuperHeroesDatabase

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, SuperHeroesDatabase::class.java).build()
        mSuperHeroesDao = db.superHeroesDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun insertListElemento_happy_case() = runBlocking {
        //given
        val superHeroeList = listOf(SuperHeroesEntity(1,
            "sdasd",
            "Sdsdd",
            "sdasd",
            "sd",
            listOf("sdsd")))
        //when
        mSuperHeroesDao.insertAllSuperHeroes(superHeroeList)
        //Then
        mSuperHeroesDao.showAllSuperHeroes().observeForever{
            assertThat(it).isNotEmpty()
            assertThat(it[0].id).isEqualTo(1)
            assertThat(it).hasSize(1)
        }
    }

    @Test
    fun obtainSuperHeroesByID() = runBlocking {
        //given
        val id = 1
        val superHeroeList = listOf(SuperHeroesEntity(1,
            "sdasd",
            "Sdsdd",
            "sdasd",
            "sd",
            listOf("sdsd")))
        //when
        mSuperHeroesDao.insertAllSuperHeroes(superHeroeList)
        // then
        mSuperHeroesDao.showOnSuperHeroesByID(id).observeForever {
            assertThat(it.id).isEqualTo(id)
        }
    }

}



