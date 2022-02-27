package com.jokopriyono.cats.model.local.favorite

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface LocalFavoriteDao {

    @Query("select * from favorites where image_id = :imageId")
    fun findFavorite(imageId: String): List<LocalFavorite>

    @Insert(onConflict = REPLACE)
    fun insertFavorite(favorite: LocalFavorite): Long

    @Delete
    fun deleteFavorite(favorite: LocalFavorite)

}