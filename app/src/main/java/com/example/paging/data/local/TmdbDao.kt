package com.example.paging.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.paging.data.entities.TmdbMovie

@Dao
interface TmdbDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<TmdbMovie>)

    @Query("SELECT * FROM movies")
    fun getAll(): PagingSource<Int, TmdbMovie>

    @Query("DELETE FROM movies")
    suspend fun clearAll()
}