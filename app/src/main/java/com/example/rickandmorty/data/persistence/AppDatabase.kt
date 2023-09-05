
package com.example.rickandmorty.data.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [RickMortyInfoDao::class,RickMortyDao::class], version = 1, exportSchema = true)
@TypeConverters(value = [MapToJsonConverter::class])
abstract class AppDatabase : RoomDatabase() {

  abstract fun rickMortyDao(): RickMortyDao
  abstract fun rickMortyInfoDao(): RickMortyInfoDao
}
