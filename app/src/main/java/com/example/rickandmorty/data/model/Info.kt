package com.example.rickandmorty.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.JsonClass

@Entity(tableName = "info")
@JsonClass(generateAdapter = true)
data class Info(

  @PrimaryKey
  @SerializedName("count") var count: Int? = null,
  @SerializedName("pages") var pages: Int? = null,
  @SerializedName("next") var next: String? = null,
  @SerializedName("prev") var prev: String? = null

)