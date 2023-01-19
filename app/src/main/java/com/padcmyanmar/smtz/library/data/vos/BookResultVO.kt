package com.padcmyanmar.smtz.library.data.vos

import com.google.gson.annotations.SerializedName

data class BookResultVO(
    @SerializedName("bestsellers_date")
    val bestsellersDate: String?,

    @SerializedName("published_date")
    val publishedDate: String?,

    @SerializedName("lists")
    val lists: List<BookListVO>?,

)