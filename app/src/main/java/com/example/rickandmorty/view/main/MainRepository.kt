
package com.example.rickandmorty.view.main

import androidx.annotation.WorkerThread
import com.example.rickandmorty.data.api.ApiRepository
import com.example.rickandmorty.data.persistence.RickMortyDao
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
    page: Int,
    onSuccess: () -> Unit,
    onError: (String) -> Unit
  ) = flow {
    var pokemons = pokemonDao.getPokemonList(page)
    if (pokemons.isEmpty()) {
      val response = pokedexClient.fetchPokemonList(page = page)


      response.let {
        println("response")
        println(Gson().toJson(response.body()))
        pokemons = response.body()!!.results
        pokemons.forEach { pokemon -> pokemon.id = page }
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
