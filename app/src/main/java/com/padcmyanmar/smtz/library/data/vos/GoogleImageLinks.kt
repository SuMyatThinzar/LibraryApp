package com.padcmyanmar.smtz.library.data.vos

import com.google.gson.annotations.SerializedName

data class GoogleImageLinks(

    @SerializedName("smallThumbnail")
    val smallThumbnail : String?,

    @SerializedName("thumbnail")
    val thumbnail : String?,


    )
