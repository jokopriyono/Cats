package com.jokopriyono.cats.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jokopriyono.cats.model.local.example.Example
import com.jokopriyono.cats.model.local.favorite.LocalFavorite
import com.jokopriyono.cats.model.local.favorite.LocalFavoriteDao

@Database(entities = [LocalFavorite::class, Example::class], version = 1)
abstract class CatDatabase : RoomDatabase() {

    abstract fun favoriteDao(): LocalFavoriteDao

    companion object {
        private var INSTANCE: CatDatabase? = null

        fun getInstance(context: Context): CatDatabase? {
            if (INSTANCE == null) {
                synchronized(CatDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        CatDatabase::class.java,
                        "cats.db"
                    ).build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}