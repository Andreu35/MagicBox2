package com.are.magicboxtwo.data.repository

import com.are.magicboxtwo.data.local.dao.FavoriteDao
import com.are.magicboxtwo.data.model.Movie
import com.are.magicboxtwo.data.remote.MagicBoxDataSource
import javax.inject.Inject

class MagicBoxRepository @Inject constructor(
    private val remoteDataSource: MagicBoxDataSource,
    private val localDataSource: FavoriteDao
) {
    /**
     * Remote
     */
    suspend fun getMovie(id: Int?) = remoteDataSource.getMovie(id)

    suspend fun searchMovies(query: String) = remoteDataSource.searchMovies(query)

    /**
     * Local Data Base
     */
    suspend fun getAllFavorites() = localDataSource.getAllFavorite()

    suspend fun insertFavorite(movie: Movie) = localDataSource.insertFavorite(movie)

    suspend fun deleteFavorite(movie: Movie) = localDataSource.deleteFavorite(movie)

    suspend fun checkIfExists(id: Int) = localDataSource.checkIfExists(id)
}