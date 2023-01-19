package com.padcmyanmar.smtz.library.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.padcmyanmar.smtz.library.data.models.LibraryModel
import com.padcmyanmar.smtz.library.data.models.LibraryModelImpl
import com.padcmyanmar.smtz.library.data.vos.BookVO
import com.padcmyanmar.smtz.library.data.vos.ShelfVO
import com.padcmyanmar.smtz.library.mvp.views.YourShelfView

class YourShelfPresenterImpl : ViewModel(), YourShelfPresenter {

    var mView: YourShelfView? = null

    //Model
    private var mLibraryModel : LibraryModel = LibraryModelImpl

    override fun initView(view: YourShelfView) {
        mView = view
    }

    override fun onUiReady(owner: LifecycleOwner) {
        mLibraryModel.getAllShelves()?.observe(owner){ shelves ->
            if(shelves.isEmpty()){
                mView?.showEmptyView()
                mView?.hideShelfList()
            } else {
                mView?.hideEmptyView()
                mView?.showShelfList(shelves)
            }
        }
    }

    override fun onTapCreateNewShelf() {
        mView?.navigateToCreateShelfScreen()
    }

    override fun onTapShelf(shelf: ShelfVO) {
        mView?.navigateToShelfDetailScreen(shelf.shelfId)
    }
}