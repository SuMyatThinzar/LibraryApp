package com.padcmyanmar.smtz.library.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.padcmyanmar.smtz.library.data.models.LibraryModel
import com.padcmyanmar.smtz.library.data.models.LibraryModelImpl
import com.padcmyanmar.smtz.library.data.vos.BookListVO
import com.padcmyanmar.smtz.library.data.vos.BookVO
import com.padcmyanmar.smtz.library.mvp.views.EbookView

class EbookPresenterImpl : ViewModel(), EbookPresenter {

    //View
    var mView: EbookView? = null

    //Model
    private var mLibraryModel : LibraryModel = LibraryModelImpl

    //States

    override fun initView(view: EbookView) {
        mView = view
    }

    override fun onUiReady(owner: LifecycleOwner) {
        mLibraryModel.getBookList {
            mView?.showError(it)
        }?.observe(owner) {
            it?.let{
                mView?.showBookLists(it)
            }
        }
    }


    // More Books Screen
    override fun onTapMoreBooks(type: Int, listName: String) {
        mView?.onTapMoreBooks(type, listName)
    }

    // Detail Screen
    override fun onTapBook(book: BookVO) {
        mView?.navigateToBookDetail(book)

        //insert book to DB
        mLibraryModel.insertRecentSingleBook(book)
    }

    // bottom sheet
    override fun onTapMoreAction(viewType: String, book: BookVO, bookListName: String) {
        mView?.onTapMoreAction(viewType, book, bookListName)
    }
}