package com.padcmyanmar.smtz.library.data.vos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "genre")
class GenreVO (

    @PrimaryKey
    @ColumnInfo(name = "listName")
    val listName : String,
    @ColumnInfo(name = "isSelected")
    var isSelected: Boolean? = false
    )