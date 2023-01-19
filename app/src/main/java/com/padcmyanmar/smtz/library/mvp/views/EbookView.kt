package com.padcmyanmar.smtz.library.mvp.views

import com.padcmyanmar.smtz.library.data.vos.BookListVO
import com.padcmyanmar.smtz.library.data.vos.BookVO

interface EbookView : BaseView {
    fun onTapMoreBooks(type: Int, listName: String)
    fun navigateToBookDetail(book: BookVO)
    fun onTapMoreAction(viewType: String, book: BookVO, bookListName: String)
    fun showBookLists(bookList: List<BookListVO>)
}