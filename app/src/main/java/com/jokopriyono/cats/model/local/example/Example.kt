package com.jokopriyono.cats.model.local.example

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

// Ini hanya contoh 2 table dalam 1 database, silahkan kamu berkreasi ya..
@Entity(tableName = "example")
@Parcelize
data class Example(
    @PrimaryKey(autoGenerate = true) var id: Long?,
    @ColumnInfo(name = "example") var example: String,
) : Parcelable
