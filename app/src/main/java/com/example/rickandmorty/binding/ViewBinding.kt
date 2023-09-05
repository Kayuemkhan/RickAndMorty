
package com.example.rickandmorty.binding

import android.app.Activity
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.OnBackPressedDispatcherOwner
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.github.florent37.glidepalette.BitmapPalette
import com.github.florent37.glidepalette.GlidePalette
import com.google.android.material.card.MaterialCardView
import com.skydoves.rainbow.Rainbow
import com.skydoves.rainbow.RainbowOrientation
import com.skydoves.rainbow.color

//@BindingAdapter("toast")
//fun bindToast(view: View, text: String?) {
//  text.isNullOrEmpty().let {
//    Toast.makeText(view.context, it, Toast.LENGTH_SHORT).show()
//  }
//}

@BindingAdapter("paletteImage", "paletteCard")
fun bindLoadImagePalette(view: AppCompatImageView, url: String, paletteCard: MaterialCardView) {
  Glide.with(view.context)
    .load(url)
    .listener(
      GlidePalette.with(url)
        .use(BitmapPalette.Profile.MUTED_LIGHT)
        .intoCallBack { palette ->
          val rgb = palette?.dominantSwatch?.rgb
          if (rgb != null) {
            paletteCard.setCardBackgroundColor(rgb)
          }
        }.crossfade(true)
    ).into(view)
}

@BindingAdapter("paletteImage", "paletteView")
fun bindLoadImagePaletteView(view: AppCompatImageView, url: String, paletteView: View) {
  val context = view.context
  Glide.with(context)
    .load(url)
    .listener(
      GlidePalette.with(url)
        .use(BitmapPalette.Profile.MUTED_LIGHT)
        .intoCallBack { palette ->
          val light = palette?.lightVibrantSwatch?.rgb
          val domain = palette?.dominantSwatch?.rgb
          if (domain != null) {
            if (light != null) {
              Rainbow(paletteView).palette {
                +color(domain)
                +color(light)
              }.background(orientation = RainbowOrientation.TOP_BOTTOM)
            } else {
              paletteView.setBackgroundColor(domain)
            }
            if (context is AppCompatActivity) {
              context.window.apply {
                addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                statusBarColor = domain
              }
            }
          }
        }.crossfade(true)
    ).into(view)
}

//@BindingAdapter("gone")
//fun bindGone(view: View, shouldBeGone: Boolean) {
//  view.gone(shouldBeGone)
//}

@BindingAdapter("onBackPressed")
fun bindOnBackPressed(view: View, finish: Boolean) {
  val context = view.context
  if (finish && context is Activity) {
    view.setOnClickListener {
      context.onBackPressed()
    }
  }
}
