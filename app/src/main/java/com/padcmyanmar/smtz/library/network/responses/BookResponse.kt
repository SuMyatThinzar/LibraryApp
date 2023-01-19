package com.padcmyanmar.smtz.library.network.responses

import com.google.gson.annotations.SerializedName
import com.padcmyanmar.smtz.library.data.vos.BookResultVO

data class BookResponse (

    @SerializedName("status")
    val status: String?,
    @SerializedName("num_results")
    val num_results: Int?,
    @SerializedName("results")
    val results: BookResultVO?,
)