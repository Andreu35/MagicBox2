package com.are.magicboxtwo.data.local.dao

import androidx.room.*
import com.are.magicboxtwo.data.model.Movie

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM Movie")
    suspend fun getAllFavorite(): List<Movie>

    @Query("SELECT EXISTS(SELECT * FROM Movie WHERE id = :movieID)")
    suspend fun checkIfExists(movieID: Int): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(vararg movies: Movie)

    @Delete
    suspend fun deleteFavorite(movie: Movie)

}