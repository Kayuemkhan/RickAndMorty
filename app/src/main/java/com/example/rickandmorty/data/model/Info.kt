package com.example.rickandmorty.data.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.JsonClass

@Entity
@JsonClass(generateAdapter = true)
data class Info(

  @SerializedName("count") var count: Int? = null,
  @SerializedName("pages") var pages: Int? = null,
  @SerializedName("next") var next: String? = null,
  @SerializedName("prev") var prev: String? = null

)