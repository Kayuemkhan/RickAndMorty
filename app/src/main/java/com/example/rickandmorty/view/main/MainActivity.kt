
package com.example.rickandmorty.view.main

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import com.example.rickandmorty.view.adapter.PokemonAdapter
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.ActivityMainBinding
import com.example.rickandmorty.utils.Constant.TAG
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.skydoves.bindables.BindingActivity
import com.skydoves.transformationlayout.onTransformationStartContainer
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {

  @get:VisibleForTesting
  internal val viewModel: MainViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    onTransformationStartContainer()
    super.onCreate(savedInstanceState)
    binding {
      adapter = PokemonAdapter()
      vm = viewModel
    }
    FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
      if (!task.isSuccessful) {
        Timber.tag(TAG).w(task.exception, "Fetching FCM registration token failed")
        return@OnCompleteListener
      }

      // Get new FCM registration token
      val token = task.result

      // Log and toast
//      val msg = getString(R.string.msg_token_fmt, token)
      Timber.tag(TAG).d(token)
      Toast.makeText(baseContext, token, Toast.LENGTH_SHORT).show()
    })
  }
}