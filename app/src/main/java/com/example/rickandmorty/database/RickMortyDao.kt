
package com.example.rickandmorty.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmorty.model.Results

@Dao
interface RickMortyDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertPokemonList(pokemonList: List<Results>)

  @Query("SELECT * FROM Results ")
  suspend fun getPokemonList(): List<Results>
}
