package com.padcmyanmar.smtz.library.mvp.presenters

import com.padcmyanmar.smtz.library.delegate.BookDetailsDelegate
import com.padcmyanmar.smtz.library.delegate.LibraryViewsDelegate
import com.padcmyanmar.smtz.library.delegate.MoreBooksDelegate
import com.padcmyanmar.smtz.library.mvp.views.MainView
import com.padcmyanmar.smtz.library.views.viewpods.EmptyShelfViewPod

interface MainPresenter : IBasePresenter {
    fun initView(view: MainView)
}