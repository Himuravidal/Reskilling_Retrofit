package com.example.reskilling.model.local

import androidx.annotation.NonNull
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "super_heroes_table")
data class SuperHeroesEntity(@PrimaryKey @NonNull val id: Int,
                             val imageXs: String,
                             val imageLg: String,
                             val name: String,
                             val alterEgos: String,
                             val height: List<String>,
                             var favorite: Boolean = false
                            )