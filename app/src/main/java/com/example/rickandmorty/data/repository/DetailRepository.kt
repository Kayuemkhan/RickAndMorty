package com.example.rickandmorty.data.repository

import androidx.annotation.WorkerThread
import com.example.rickandmorty.database.RickMortyInfoDao
import com.example.rickandmorty.network.model.CharacterDetailsResponse
import com.example.rickandmorty.network.service.ApiRepository
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DetailRepository @Inject constructor(
  private val pokedexClient: ApiRepository,
  private val pokemonInfoDao: RickMortyInfoDao
) : Repository {

  @WorkerThread
  suspend fun fetchPokemonInfo(
    name: Int,
    onSuccess: () -> Unit,
    onError: (String) -> Unit
  ) = flow {
    var pokemonInfo = pokemonInfoDao.getRickMortyInfo()
    if (pokemonInfo == null) {
      val response = pokedexClient.fetchPokemonInfo(name)
      response.let {
        pokemonInfo = response.body()
        println("responseAA")
        println(Gson().toJson(response.body()))
        pokemonInfoDao.insertPokemonInfo(response.body()!!)
        emit(pokemonInfo)
        onSuccess()

      }

    } else {
      emit(pokemonInfo)
      onSuccess()
    }
  }.flowOn(Dispatchers.IO)
}
