package com.example.rickandmorty.utils

import android.Manifest.permission

object Constant {
  const val BASE_URL: String = "https://rickandmortyapi.com/api"


  const val PERMISSION_REQUEST_CODE = 100
  const val PHONE_STATE_PERMISSION = permission.READ_PHONE_STATE

  const val TAG = "RICKY_LOG"

  const val REQUEST_TIMEOUT_SECONDS:Long = 20

}