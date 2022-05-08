package com.example.paging.di


import android.content.Context
import androidx.room.Room
import com.example.paging.data.local.TmdbLocalDatabase
import com.example.paging.data.remote.TmdbApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(TmdbApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Singleton
    @Provides
    fun provideTmdbApi(retrofit: Retrofit): TmdbApi =
        retrofit.create(TmdbApi::class.java)

    @Singleton
    @Provides
    fun provideTmdbDatabase(
        @ApplicationContext context: Context
    ): TmdbLocalDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            TmdbLocalDatabase::class.java,
            "tmdb_local_database"
        ).build()
    }
}