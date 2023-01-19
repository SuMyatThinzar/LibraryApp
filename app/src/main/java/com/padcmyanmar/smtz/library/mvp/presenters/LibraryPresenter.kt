package com.padcmyanmar.smtz.library.mvp.presenters

import com.padcmyanmar.smtz.library.mvp.views.LibraryView

interface LibraryPresenter : IBasePresenter {
    fun initView(view: LibraryView)
}