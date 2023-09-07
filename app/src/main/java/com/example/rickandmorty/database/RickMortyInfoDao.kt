
package com.example.rickandmorty.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmorty.model.Info
import com.example.rickandmorty.network.model.CharacterDetailsResponse

@Dao
interface RickMortyInfoDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertPokemonInfo(info: CharacterDetailsResponse)

  @Query("SELECT * FROM info")
  suspend fun getRickMortyInfo(): CharacterDetailsResponse?
}
