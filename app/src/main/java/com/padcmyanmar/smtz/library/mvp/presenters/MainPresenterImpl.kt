package com.padcmyanmar.smtz.library.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.padcmyanmar.smtz.library.data.models.LibraryModel
import com.padcmyanmar.smtz.library.data.models.LibraryModelImpl
import com.padcmyanmar.smtz.library.data.vos.BookVO
import com.padcmyanmar.smtz.library.mvp.views.MainView

class MainPresenterImpl : ViewModel(), MainPresenter {

    var mView: MainView? = null

    override fun initView(view: MainView) {
        mView = view
    }

    override fun onUiReady(owner: LifecycleOwner) {
//        mLibraryModel.getBookList {
//            mView?.showError(it)
//        }?.observe(owner) {
//            mView?.showBookLists(it)
//        }
    }

//    override fun onTapMoreBooks(type: Int) {
//        mView?.navigateToMoreEbookAndAudiobookActivity(type)
//    }
//
//
//    // Detail Screen
//    override fun onTapBook() {
//        mView?.navigateToBookDetailActivity()
//    }
//
//    // bottom sheet (Grid more and List more from Library)
//    override fun onTapMoreAction(viewType: String) {
//        mView?.onTapMoreActionBottomSheet(viewType)
//    }
//
//    // change views in List, Large Grid, Small Grid
//    override fun onTapView() {
//        mView?.onTapChangeViewStyle()
//    }
//
//    override fun onTapCreateNewShelf() {
//
//
//    }
}