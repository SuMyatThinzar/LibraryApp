package com.padcmyanmar.smtz.library.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.padcmyanmar.smtz.library.data.vos.BookVO
import com.padcmyanmar.smtz.library.delegate.AddToShelfCheckBoxDelegate
import com.padcmyanmar.smtz.library.mvp.views.AddToShelfView

interface AddToShelfPresenter : IBasePresenter, AddToShelfCheckBoxDelegate {
    fun initView(view: AddToShelfView)
    fun onUiReadyAddToShelf(owner: LifecycleOwner, book: BookVO, bookListName: String)
    fun onTapAddToShelfConfirm()
}