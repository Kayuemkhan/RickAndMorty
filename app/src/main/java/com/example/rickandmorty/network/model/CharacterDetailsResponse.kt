package com.example.rickandmorty.network.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Entity(tableName = "character_details")
@Parcelize
data class CharacterDetailsResponse (

  @PrimaryKey(autoGenerate = true)
  @SerializedName("id"       ) var id       : Int?      = null,
  @SerializedName("name"     ) var name     : String?   = null,
  @SerializedName("status"   ) var status   : String?   = null,
  @SerializedName("species"  ) var species  : String?   = null,
  @SerializedName("type"     ) var type     : String?   = null,
  @SerializedName("gender"   ) var gender   : String?   = null,
  @SerializedName("origin"   ) var origin   : Origin?   = Origin(),
  @SerializedName("location" ) var location : Location? = Location(),
  @SerializedName("image"    ) var image    : String?   = null,
  @SerializedName("episode"  ) var episode  : String?   = null,
  @SerializedName("url"      ) var url      : String?   = null,
  @SerializedName("created"  ) var created  : String?   = null

): Parcelable