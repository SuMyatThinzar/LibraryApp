package com.padcmyanmar.smtz.library.network.responses

import com.google.gson.annotations.SerializedName
import com.padcmyanmar.smtz.library.data.vos.GoogleBookVO

data class GoogleBookResponse(

    @SerializedName("kind")
    val kind: String?,

    @SerializedName("totalItems")
    val totalItems: Int?,

    @SerializedName("items")
    val items: List<GoogleBookVO>?,
)