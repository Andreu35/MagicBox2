package com.are.magicboxtwo.di

import android.content.Context
import com.are.magicboxtwo.R
import com.are.magicboxtwo.data.local.AppDataBase
import com.are.magicboxtwo.data.local.dao.FavoriteDao
import com.are.magicboxtwo.data.pref.MagicBoxPreferences
import com.are.magicboxtwo.data.remote.MagicBoxDataSource
import com.are.magicboxtwo.data.remote.net.MagicBoxAPI
import com.are.magicboxtwo.data.repository.MagicBoxRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson, @ApplicationContext appContext: Context): Retrofit =
        Retrofit.Builder()
            .baseUrl(appContext.getString(R.string.base_url))
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    @Singleton
    @Provides
    fun provideGSON(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideAPIService(retrofit: Retrofit): MagicBoxAPI =
        retrofit.create(MagicBoxAPI::class.java)

    @Singleton
    @Provides
    fun provideRemoteDataSource(api: MagicBoxAPI, @ApplicationContext appContext: Context) =
        MagicBoxDataSource(api, appContext)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) =
        AppDataBase.getInstance(appContext)

    @Singleton
    @Provides
    fun provideFavoriteDao(db: AppDataBase) = db.favoriteDao()

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: MagicBoxDataSource, localDataSource: FavoriteDao) =
        MagicBoxRepository(remoteDataSource, localDataSource)

    @Singleton
    @Provides
    fun providePreferences(@ApplicationContext appContext: Context) =
        MagicBoxPreferences(appContext)
}