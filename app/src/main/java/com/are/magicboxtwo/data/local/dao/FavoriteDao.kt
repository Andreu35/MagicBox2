package com.are.magicboxtwo.data.local.dao

import androidx.room.*
import com.are.magicboxtwo.data.model.Movie

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM Movie")
    fun getAllFavorite(): List<Movie>

    @Query("SELECT EXISTS(SELECT * FROM Movie WHERE id = :movieID)")
    fun checkIfExists(movieID: Int): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavorite(vararg movies: Movie)

    @Delete
    fun deleteFavorite(movie: Movie)

}