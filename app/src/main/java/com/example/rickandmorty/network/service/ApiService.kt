package com.example.rickandmorty.network.service


import com.example.rickandmorty.network.model.CharacterDetailsResponse
import com.example.rickandmorty.network.model.CharacterResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
  @GET("character")
  suspend fun fetchPokemonList(
    @Query("page") page: Int = 1,
  ): Response<CharacterResponse>

  @GET("character/{characterId}")
  suspend fun fetchPokemonInfo(
    @Path("characterId") characterId: Int
  ): Response<CharacterDetailsResponse>

}
