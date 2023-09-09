package com.example.rickandmorty.data.repository

import androidx.annotation.WorkerThread
import com.example.rickandmorty.database.RickMortyDao
import com.example.rickandmorty.network.service.ApiRepository
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DetailRepository @Inject constructor(
  private val apiRepository: ApiRepository,
  private val pokemonInfoDao: RickMortyDao
) : Repository {

  @WorkerThread
  suspend fun fetchPokemonInfo(
    name: Int,
    onSuccess: () -> Unit,
    onError: (String) -> Unit
  ) = flow {

      val response = apiRepository.fetchPokemonInfo(name)
      response.let {
        val pokemonInfo = response.body()
        println("pokemonInforesponse")
        println(Gson().toJson(response.body()))

        emit(pokemonInfo)
        onSuccess()

      }

  }.flowOn(Dispatchers.IO)
}
