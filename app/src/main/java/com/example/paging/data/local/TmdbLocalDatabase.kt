package com.example.paging.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.paging.data.entities.TmdbMovie

@Database(entities = [TmdbMovie::class], version = 3)
abstract class TmdbLocalDatabase: RoomDatabase() {
    abstract val moviesDao: TmdbDao
}