
package com.example.rickandmorty.ui.main

import androidx.annotation.WorkerThread
import androidx.room.Dao
import com.example.rickandmorty.data.api.ApiRepository
import com.example.rickandmorty.data.persistence.RickMortyDao
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
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
      response.suspendOnSuccess {
        data.let {
            response ->
            pokemons = response.results
            pokemons.forEach { pokemon -> pokemon.id = page }
            pokemonDao.insertPokemonList(pokemons)
            emit(pokemons)
            onSuccess()
          }
        }

        // handle the case when the API request gets an error response.
        // e.g. internal server error.
        .onError {
          onError(message())
        }
        // handle the case when the API request gets an exception response.
        // e.g. network connection error.
        .onException {
          onError(message())
        }
    } else {
      emit(pokemons)
      onSuccess()
    }
  }.flowOn(Dispatchers.IO)
}
