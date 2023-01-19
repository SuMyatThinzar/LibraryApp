package com.padcmyanmar.smtz.library.mvp.views

import com.padcmyanmar.smtz.library.data.vos.BookVO

interface HomeView : BaseView {
    fun onTapMoreAction(viewType: String, book: BookVO, bookListName: String)
    fun navigateToBookDetail(book: BookVO)
    fun showRecentBookList(books: List<BookVO>)
    fun showEmptyView()
    fun hideEmptyView()
}