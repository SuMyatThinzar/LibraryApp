package com.padcmyanmar.smtz.library.data.vos

import com.google.gson.annotations.SerializedName

// volumeInfo
data class GoogleVolumeInfo (

    @SerializedName("title")
    val title : String?,

    @SerializedName("subtitle")
    val subtitle : String?,

    @SerializedName("authors")
    val authors : List<String>?,

    @SerializedName("categories")
    val categories : List<String>?,

    @SerializedName("publisher")
    val publisher : String?,

    @SerializedName("publishedDate")
    val publishedDate : String?,

    @SerializedName("description")
    val description : String?,

    @SerializedName("imageLinks")
    val imageLinks : GoogleImageLinks?,


    )