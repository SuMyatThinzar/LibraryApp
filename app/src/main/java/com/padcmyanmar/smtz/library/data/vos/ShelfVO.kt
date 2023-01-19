package com.padcmyanmar.smtz.library.data.vos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.padcmyanmar.smtz.library.persistance.typeconverters.BookTypeConverter


@Entity(tableName = "shelves")
@TypeConverters(
    BookTypeConverter::class
)

data class ShelfVO(
    @PrimaryKey
    @ColumnInfo(name = "shelf_id")
    @SerializedName("shelf_id")
    val shelfId: Long,

    @ColumnInfo(name = "shelf_name")
    @SerializedName("shelf_name")
    val shelfName: String,

    @ColumnInfo(name = "books")
    @SerializedName("books")
    val books: MutableList<BookVO> = mutableListOf(),

    var isChecked: Boolean = false
)