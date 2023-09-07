package com.example.rickandmorty.network.service
import com.example.rickandmorty.network.model.CharacterDetailsResponse
import com.example.rickandmorty.network.model.CharacterResponse
import retrofit2.Response
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private val apiService: ApiService,
) {

  suspend fun fetchPokemonList(
    page: Int
  ): Response<CharacterResponse>
  =
        apiService.fetchPokemonList(page)

  suspend fun fetchPokemonInfo(
    id: Int
  ): Response<CharacterDetailsResponse>
  =
        apiService.fetchPokemonInfo(id)



}
