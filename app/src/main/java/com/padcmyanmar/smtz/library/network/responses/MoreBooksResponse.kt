package com.padcmyanmar.smtz.library.network.responses

import com.padcmyanmar.smtz.library.data.vos.Result

data class MoreBooksResponse(
    val copyright: String,
    val last_modified: String,
    val num_results: Int,
    val status: String,
    val results: List<Result>
)