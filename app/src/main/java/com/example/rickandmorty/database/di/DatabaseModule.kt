package com.example.rickandmorty.database.di

import android.app.Application
import androidx.room.Room
import com.example.rickandmorty.database.AppDatabase
import com.example.rickandmorty.database.RickMortyDao
import com.example.rickandmorty.network.model.CharacterDetailsResponse
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): AppDatabase {
        return Room
            .databaseBuilder(application, AppDatabase::class.java, "application.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideRickMortyDao(appDatabase: AppDatabase): RickMortyDao {
        return appDatabase.rickMortyDao()
    }

//    @Provides
//    @Singleton
//    fun provideRickMortyInfoDao(appDatabase: AppDatabase): CharacterDetailsResponse {
//        return appDatabase.rickMortyInfoDao()
//    }
}