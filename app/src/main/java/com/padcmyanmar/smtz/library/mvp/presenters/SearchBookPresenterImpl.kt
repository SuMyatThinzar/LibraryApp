package com.padcmyanmar.smtz.library.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.padcmyanmar.smtz.library.data.models.LibraryModel
import com.padcmyanmar.smtz.library.data.models.LibraryModelImpl
import com.padcmyanmar.smtz.library.data.vos.BookVO
import com.padcmyanmar.smtz.library.mvp.views.SearchBookView

class SearchBookPresenterImpl : ViewModel(), SearchBookPresenter {

    private var mView: SearchBookView? = null

    //Model
    private var mLibraryModel: LibraryModel = LibraryModelImpl

    override fun initView(view: SearchBookView) {
        mView = view
    }

    override fun onUiReady(owner: LifecycleOwner) {

    }

    override fun onTapBook(book: BookVO) {

        mView?.navigateToBookDetail(book)

        //insert book to DB
        mLibraryModel.insertRecentSingleBook(book)
    }

    override fun onTapMoreAction(viewType: String, book: BookVO, bookListName: String) {

    }

}