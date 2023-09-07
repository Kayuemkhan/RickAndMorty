
package com.example.rickandmorty.data.repository

import androidx.annotation.WorkerThread
import com.example.rickandmorty.network.service.ApiRepository
import com.example.rickandmorty.database.RickMortyDao
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MainRepository @Inject constructor(
  private val pokedexClient: ApiRepository,
  private val pokemonDao: RickMortyDao
) : Repository {



  @WorkerThread
  suspend fun fetchPokemonList(
    onSuccess: () -> Unit,
    onError: (String) -> Unit
  ) = flow {
    var pokemons = pokemonDao.getPokemonList()
    if (pokemons.isEmpty()) {
      val response = pokedexClient.fetchPokemonList(1)


      response.let {
        println("response")
        println(Gson().toJson(response.body()))
        pokemons = response.body()!!.results
        pokemonDao.insertPokemonList(pokemons)
        emit(pokemons)
        onSuccess()

      }




    } else {
      emit(pokemons)
      onSuccess()
    }
  }.flowOn(Dispatchers.IO)
}
