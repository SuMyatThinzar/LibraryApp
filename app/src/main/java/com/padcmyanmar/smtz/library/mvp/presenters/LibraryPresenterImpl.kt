package com.padcmyanmar.smtz.library.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.padcmyanmar.smtz.library.data.vos.BookVO
import com.padcmyanmar.smtz.library.mvp.views.LibraryView

class LibraryPresenterImpl : ViewModel(), LibraryPresenter {

    var mView: LibraryView? = null

    override fun initView(view: LibraryView) {
        mView = view
    }

    override fun onUiReady(owner: LifecycleOwner) {

    }
}