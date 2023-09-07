
package com.example.rickandmorty.data.di

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val rickyMortyAppDispatchers: RickyMortyAppDispatchers)

enum class RickyMortyAppDispatchers {
  IO
}
