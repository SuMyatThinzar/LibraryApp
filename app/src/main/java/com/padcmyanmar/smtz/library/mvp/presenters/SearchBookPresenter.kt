package com.padcmyanmar.smtz.library.mvp.presenters

import com.padcmyanmar.smtz.library.delegate.BookDetailsDelegate
import com.padcmyanmar.smtz.library.mvp.views.SearchBookView

interface SearchBookPresenter : IBasePresenter, BookDetailsDelegate {
    fun initView(view: SearchBookView)
}