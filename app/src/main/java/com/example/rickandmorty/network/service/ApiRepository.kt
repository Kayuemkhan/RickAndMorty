package com.example.rickandmorty.network.service
import com.example.rickandmorty.network.model.ApiResponse
import com.example.rickandmorty.network.model.CharacterResponse
import retrofit2.Response
import javax.inject.Inject

class ApiRepository @Inject constructor(
  private val apiService: ApiService,
  private val apiResponse: ApiResponse,
) {

  suspend fun fetchPokemonList(
    page: Int
  ): Response<CharacterResponse>
  =
        apiService.fetchPokemonList(page)



}
