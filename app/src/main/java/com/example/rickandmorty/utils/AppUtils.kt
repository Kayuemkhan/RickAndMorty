package com.example.rickandmorty.utils

import android.Manifest.permission
import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.os.Build.VERSION
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.core.app.ActivityCompat
import com.google.android.material.snackbar.Snackbar
import java.util.Random

object AppUtils {
  val phoneStatePermisison = arrayOf(permission.READ_PHONE_STATE)



  fun randomColorFromList(): String {
    val givenList = listOf(
      "#8B4E9A", "#5F61AB", "#FC7794", "#9688FC", "#477591", "#7A536A",
      "#30C6C6", "#FF7A4A"
    )
    val rand = Random()
    return givenList[rand.nextInt(givenList.size)]
  }


  fun isNetworkAvailable(context: Context): Boolean {
    val connectivityManager =
      (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
    val activeNetworkInfo = connectivityManager.activeNetworkInfo
    return activeNetworkInfo != null && activeNetworkInfo.isConnected
  }

  fun message(view: View?, msg: String?, textColor: Int, backgroundColor: Int) {
    Log.d("ErrorSnackCalled","Error Snack is showing")
    if (view == null) return
    val snack = Snackbar.make(view, msg!!, Snackbar.LENGTH_SHORT)
    val snackBarView = snack.view
    snackBarView.setBackgroundColor(backgroundColor)
    val snackBarText =
      snackBarView.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
    snackBarText.setTextColor(textColor)
    snack.show()
  }


  @SuppressLint("ObsoleteSdkInt")
  fun hasPermissionList(permissionList: Array<String>, context: Context): Boolean {
    var res = true
    for (permission in permissionList) {
      if (!hasPermission(permission, context)) {
        res = false
      }
    }
    if (VERSION.SDK_INT < 22) {
      res = true
    }
    return res
  }

  fun hasPermission(permission: String, context: Context): Boolean {
    return ActivityCompat.checkSelfPermission(context, permission) == 0
  }


}