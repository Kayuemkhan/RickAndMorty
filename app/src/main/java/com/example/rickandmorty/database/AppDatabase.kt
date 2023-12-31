
package com.example.rickandmorty.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.rickandmorty.model.Results

@Database(entities = [Results::class], version = 2, exportSchema = true)
@TypeConverters(value = [MapToJsonConverter::class])
abstract class AppDatabase : RoomDatabase() {

  abstract fun rickMortyDao(): RickMortyDao
//  abstract fun rickMortyInfoDao(): RickMortyInfoDao
}
