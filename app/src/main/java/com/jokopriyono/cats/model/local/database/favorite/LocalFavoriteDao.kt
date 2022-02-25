package com.jokopriyono.cats.model.local.database.favorite

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface LocalFavoriteDao {

    @Query("select * from favorites")
    fun findFavorite(): LocalFavorite?

    @Insert(onConflict = REPLACE)
    fun insertFavorite(favorite: LocalFavorite): Long

    @Delete
    fun deleteFavorite(favorite: LocalFavorite)

}