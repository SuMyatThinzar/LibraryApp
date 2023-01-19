package com.padcmyanmar.smtz.library.data.vos

data class Result(
    val amazon_product_url: String,
    val asterisk: Int,
    val bestsellers_date: String,
    val dagger: Int,
    val display_name: String,
    val list_name: String,
    val published_date: String,
    val rank: Int,
    val rank_last_week: Int,
    val weeks_on_list: Int,
    val book_details: List<BookVO>,
)