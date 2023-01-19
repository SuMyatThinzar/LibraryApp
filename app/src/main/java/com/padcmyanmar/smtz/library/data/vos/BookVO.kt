package com.padcmyanmar.smtz.library.data.vos

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "book")
data class BookVO (

    @ColumnInfo(name = "age_group")
    @SerializedName("age_group")
    val ageGroup : String?,

    @ColumnInfo(name = "author")
    @SerializedName("author")
    val author : String?,

//    @ColumnInfo(name = "amazon_product_url")
//    @SerializedName("amazon_product_url")
//    val amazonProductUrl : String?,

    @ColumnInfo(name = "book_image")
    @SerializedName("book_image")
    val bookImage : String?,

//    @ColumnInfo(name = "contributor")
//    @SerializedName("contributor")
//    val contributor : String?,

//    @ColumnInfo(name = "contributor_note")
//    @SerializedName("contributor_note")
//    val contributorNote : String?,

    @ColumnInfo(name = "created_date")
    @SerializedName("created_date")
    val createDate : String?,

    @ColumnInfo(name = "description")
    @SerializedName("description")
    val description : String?,

    @ColumnInfo(name = "price")
    @SerializedName("price")
    val price : Int?,

//    @ColumnInfo(name = "primary_isbn13")
//    @SerializedName("primary_isbn13")
//    val primaryIsbn13 : String?,

//    @ColumnInfo(name = "primary_isbn10")
//    @SerializedName("primary_isbn10")
//    val primaryIsbn10 : String?,

    @ColumnInfo(name = "publisher")
    @SerializedName("publisher")
    val publisher : String?,

    @ColumnInfo(name = "rank")
    @SerializedName("rank")
    val rank : Int?,

    @ColumnInfo(name = "rank_last_week")
    @SerializedName("rank_last_week")
    val rankLastWeek : Int?,

    @PrimaryKey
    @ColumnInfo(name = "title")
    @SerializedName("title")
    val title : String,

    @ColumnInfo(name = "updated_date")
    @SerializedName("updated_date")
    val updatedDate : String?,

    @ColumnInfo(name = "book_list_name")
    @SerializedName("book_list_name")
    var bookListName : String?,

    ) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
//        parcel.readString(),
//        parcel.readString(),
//        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
//        parcel.readString(),
//        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString()!!,
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(ageGroup)
        parcel.writeString(author)
//        parcel.writeString(amazonProductUrl)
        parcel.writeString(bookImage)
//        parcel.writeString(contributor)
//        parcel.writeString(contributorNote)
        parcel.writeString(createDate)
        parcel.writeString(description)
        parcel.writeValue(price)
//        parcel.writeString(primaryIsbn13)
//        parcel.writeString(primaryIsbn10)
        parcel.writeString(publisher)
        parcel.writeValue(rank)
        parcel.writeValue(rankLastWeek)
        parcel.writeString(title)
        parcel.writeString(updatedDate)
        parcel.writeString(bookListName)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BookVO> {
        override fun createFromParcel(parcel: Parcel): BookVO {
            return BookVO(parcel)
        }

        override fun newArray(size: Int): Array<BookVO?> {
            return arrayOfNulls(size)
        }
    }
}