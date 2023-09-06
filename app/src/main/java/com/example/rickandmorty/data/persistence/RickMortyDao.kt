
package com.example.rickandmorty.data.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmorty.data.model.Results

@Dao
interface RickMortyDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertPokemonList(pokemonList: List<Results>)

  @Query("SELECT * FROM Results WHERE id = :page_")
  suspend fun getPokemonList(page_: Int): List<Results>
}
