package com.padcmyanmar.smtz.library.mvp.views

import com.padcmyanmar.smtz.library.data.vos.BookVO

interface SearchBookView : BaseView {
    fun navigateToBookDetail(book: BookVO)
}