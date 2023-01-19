package com.padcmyanmar.smtz.library.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.padcmyanmar.smtz.library.data.models.LibraryModel
import com.padcmyanmar.smtz.library.data.models.LibraryModelImpl
import com.padcmyanmar.smtz.library.data.vos.BookVO
import com.padcmyanmar.smtz.library.data.vos.ShelfVO
import com.padcmyanmar.smtz.library.mvp.views.CreateShelfView

class CreateShelfPresenterImpl : ViewModel(), CreateShelfPresenter {

    //View
    var mView: CreateShelfView? = null

    //Model
    private var mLibraryModel : LibraryModel = LibraryModelImpl

    //States

    override fun initView(view: CreateShelfView) {
        mView = view
    }

    override fun onUiReady(owner: LifecycleOwner) {

    }

    override fun onTapComplete(shelfName: String) {

        if (shelfName.isNotEmpty()){

            val shelfVO = ShelfVO(shelfId = System.currentTimeMillis(), shelfName = shelfName)

            mLibraryModel.insertShelf(shelfVO)

            mView?.insertShelfComplete()
        }
    }

    override fun onTapBack() {
        mView?.navigateToBackScreen()
    }

}