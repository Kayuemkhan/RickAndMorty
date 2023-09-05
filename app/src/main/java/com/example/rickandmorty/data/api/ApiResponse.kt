package com.gplex.wallboard.data.api

import android.content.Context
import android.util.Log
import com.example.rickandmorty.utils.AppUtils
import com.example.rickandmorty.utils.Constant.TAG
import org.json.JSONObject
import retrofit2.Response
import java.io.IOException
import java.net.SocketException
import java.net.SocketTimeoutException
import javax.inject.Inject

class ApiResponse @Inject constructor(private val context: Context) : IOException() {


  suspend fun <T : Any> getResult(call: suspend () -> Response<T>): T {


    if (!AppUtils.isNetworkAvailable(context)) {
      throw IOException("Oh! You are not connected to a wifi or cellular data network. Please connect and try again")
    }
    val response = try {
      call.invoke()

    } catch (e: SocketException) {
      Log.e(TAG, "getResult: "+e.localizedMessage)
      Log.e(TAG, "getResult: $e")
      throw IOException(e.localizedMessage!!)
    } catch (e: IOException) {
      Log.e(TAG, "getResult: "+e.localizedMessage)
      Log.e(TAG, "getResult: $e")

      throw IOException(e.localizedMessage!!)
    } catch (e: SocketTimeoutException) {
      Log.e(TAG, "getResult: "+e.localizedMessage)
      Log.e(TAG, "getResult: $e")
      throw IOException(e.localizedMessage!!)
    } catch (e: Exception) {
      Log.e(TAG, "getResult: "+e.localizedMessage)
      Log.e(TAG, "getResult: $e")
      throw IOException(e.localizedMessage!!)
    }

    if (response.isSuccessful) {
      return response.body()!!
    } else if (response.code() == 503) {
      Log.d("req_url",call.invoke().toString());
      Log.d("req_url",response.toString())
      throw IOException("Service temporarily unavailable")
    } else if (
      response.code() == 400 || response.code() == 401 || response.code() == 403 || response.code() == 404 ||
      response.code() == 406 || response.code() == 422 || response.code() == 451 || response.code() == 501 ||
      response.code() == 500
    ) {
      throw IOException("Something went wrong")
    } else {
      Log.d("errorHappened","Error Happened")
      val errorBody = JSONObject(response.errorBody()?.toString()!!)
      if (errorBody.has("error_description")) {
        val errorMessage = errorBody.optString("error_description")
        throw IOException(errorMessage)
      } else {
        val errorTitle = errorBody.keys().next()
        val errorMessage = errorBody.optString(errorTitle)
        throw IOException(errorMessage.toString())
      }
    }
  }


}