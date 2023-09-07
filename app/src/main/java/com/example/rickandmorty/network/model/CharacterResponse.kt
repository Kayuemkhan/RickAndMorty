package com.example.rickandmorty.network.model

import com.example.rickandmorty.model.Info
import com.example.rickandmorty.model.Results
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharacterResponse(

  @SerializedName("info") var info: Info? = Info(),
  @SerializedName("results") var results: ArrayList<Results> = arrayListOf()

)