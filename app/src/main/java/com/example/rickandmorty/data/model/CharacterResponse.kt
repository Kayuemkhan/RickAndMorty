package com.example.rickandmorty.data.model

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharacterResponse(

  @SerializedName("info") var info: Info? = Info(),
  @SerializedName("results") var results: ArrayList<Results> = arrayListOf()

)