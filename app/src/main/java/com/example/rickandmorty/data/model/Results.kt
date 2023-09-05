package com.example.rickandmorty.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
@JsonClass(generateAdapter = true)
data class Results(

  @SerializedName("id")@PrimaryKey  var id: Int? = null,
  @SerializedName("name") var name: String? = null,
  @SerializedName("status") var status: String? = null,
  @SerializedName("species") var species: String? = null,
  @SerializedName("type") var type: String? = null,
  @SerializedName("gender") var gender: String? = null,
  @SerializedName("image") var image: String? = null,
  @SerializedName("url") var url: String? = null,
  @SerializedName("created") var created: String? = null

) : Parcelable