package com.padcmyanmar.smtz.library.data.vos

import androidx.room.*
import com.google.gson.annotations.SerializedName
import com.padcmyanmar.smtz.library.persistance.typeconverters.BookTypeConverter

@Entity(tableName = "bookList")
@TypeConverters(
    BookTypeConverter::class
)
data class BookListVO(

    @ColumnInfo(name = "book")
    @SerializedName("books")
    val books: List<BookVO>?,

    @ColumnInfo(name = "display_name")
    @SerializedName("display_name")
    val displayName: String?,

    @PrimaryKey
    @ColumnInfo(name = "list_id")
    @SerializedName("list_id")
    val listId: Int?,

    @ColumnInfo(name = "list_image")
    @SerializedName("list_image")
    val listImage: String?,

    @ColumnInfo(name = "list_name")
    @SerializedName("list_name")
    val listName: String?,

    @ColumnInfo(name = "updated")
    @SerializedName("updated")
    val updated: String?
)