package com.padcmyanmar.smtz.library.data.vos

import com.google.gson.annotations.SerializedName

//items Obj
data class GoogleBookVO (

    @SerializedName("kind")
    val kind : String?,

    @SerializedName("id")
    val id : String?,

    @SerializedName("etag")
    val etag : String?,

    @SerializedName("selfLink")
    val selfLink : String?,

    @SerializedName("volumeInfo")
    val volumeInfo : GoogleVolumeInfo?

    ) {

    fun googleBookToBookVO(): BookVO {
        return BookVO(
            ageGroup = "",
            author = "${volumeInfo?.authors?.getOrNull(0)}",
            bookImage = volumeInfo?.imageLinks?.thumbnail,
            createDate = "",
            description = volumeInfo?.description,
            price = 0,
            publisher = "",
            rank = 0,
            rankLastWeek = 0,
            title = volumeInfo?.title.toString(),
            updatedDate = "",
            bookListName = "${volumeInfo?.categories?.getOrNull(0)}"
        )
    }

}