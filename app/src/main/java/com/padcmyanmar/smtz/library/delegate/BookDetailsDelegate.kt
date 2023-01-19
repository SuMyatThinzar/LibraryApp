package com.padcmyanmar.smtz.library.delegate

import com.padcmyanmar.smtz.library.data.vos.BookVO

interface BookDetailsDelegate {
    fun onTapBook(book: BookVO)
    fun onTapMoreAction(viewType: String, book: BookVO, bookListName: String)
}