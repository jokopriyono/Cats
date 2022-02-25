package com.jokopriyono.cats.model.local.database.favorite

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "favorites")
@Parcelize
data class LocalFavorite(
    @PrimaryKey(autoGenerate = true) var id: Long?,
    @ColumnInfo(name = "image_id") var imageId: String,
    @ColumnInfo(name = "image_url") var imageUrl: String,
) : Parcelable
