package com.jokopriyono.cats.model.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jokopriyono.cats.model.local.database.favorite.LocalFavorite
import com.jokopriyono.cats.model.local.database.favorite.LocalFavoriteDao

@Database(entities = [LocalFavorite::class], version = 1)
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