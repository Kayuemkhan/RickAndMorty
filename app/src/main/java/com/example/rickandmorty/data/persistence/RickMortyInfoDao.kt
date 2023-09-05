
package com.example.rickandmorty.data.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmorty.data.model.Info

@Dao
interface RickMortyInfoDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertPokemonInfo(info: Info)

  @Query("SELECT * FROM Info WHERE name = :name_")
  suspend fun getPokemonInfo(name_: String): Info?
}
