package com.padcmyanmar.smtz.library.mvp.presenters

import com.padcmyanmar.smtz.library.delegate.BookDetailsDelegate
import com.padcmyanmar.smtz.library.delegate.MoreBooksDelegate
import com.padcmyanmar.smtz.library.mvp.views.EbookView

interface EbookPresenter : IBasePresenter, MoreBooksDelegate, BookDetailsDelegate {
    fun initView(view: EbookView)
}