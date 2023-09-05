package com.example.rickandmorty.data.di

import android.app.Application
import android.content.Context
import android.os.Build
import androidx.room.Room
import com.example.rickandmorty.data.persistence.AppDatabase
import com.example.rickandmorty.data.persistence.RickMortyDao
import com.example.rickandmorty.data.persistence.RickMortyInfoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Provides
    @Singleton
    fun provideRickMortyInfoDao(appDatabase: AppDatabase): RickMortyInfoDao {
        return appDatabase.rickMortyInfoDao()
    }
}