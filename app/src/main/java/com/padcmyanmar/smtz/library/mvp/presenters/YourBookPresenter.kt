package com.padcmyanmar.smtz.library.mvp.presenters

import com.padcmyanmar.smtz.library.delegate.BookDetailsDelegate
import com.padcmyanmar.smtz.library.delegate.GenreDelegate
import com.padcmyanmar.smtz.library.delegate.LibraryViewsDelegate
import com.padcmyanmar.smtz.library.delegate.SortBooksDelegate
import com.padcmyanmar.smtz.library.mvp.views.YourBookView

interface YourBookPresenter : IBasePresenter, BookDetailsDelegate, LibraryViewsDelegate, GenreDelegate, SortBooksDelegate {
    fun initView(view: YourBookView)
}