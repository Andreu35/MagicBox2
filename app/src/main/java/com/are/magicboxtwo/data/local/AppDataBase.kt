package com.are.magicboxtwo.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.are.magicboxtwo.data.local.converter.GenreConverter
import com.are.magicboxtwo.data.local.dao.FavoriteDao
import com.are.magicboxtwo.data.model.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = false)
@TypeConverters(value = [GenreConverter::class])
abstract class AppDataBase: RoomDatabase() {

    abstract fun favoriteDao(): FavoriteDao

    companion object {
        @Volatile private var INSTANCE: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, AppDataBase::class.java, "app.db").build()
    }
}