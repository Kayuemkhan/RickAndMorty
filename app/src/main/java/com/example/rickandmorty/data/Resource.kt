package com.example.rickandmorty.data

data class Resource<T>(
  val status: Status,
  val data: T?,
  val message: String?
) {

  companion object {

    fun <T> success(msg: String, data: T?): Resource<T> = Resource(Status.SUCCESS, data, msg)
    fun <T> loading(msg: String, data: T?): Resource<T> = Resource(Status.LOADING, data, msg)
    fun <T> error(msg: String, data: T?): Resource<T> = Resource(Status.ERROR, data, msg)
    fun <T> unregistered(msg: String, data: T?): Resource<T> = Resource(Status.UNREGISTERED, data, msg)
  }
}

enum class Status {
  SUCCESS,
  ERROR,
  LOADING,
  UNREGISTERED
}