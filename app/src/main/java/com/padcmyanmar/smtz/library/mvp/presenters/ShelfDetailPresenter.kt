package com.padcmyanmar.smtz.library.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.padcmyanmar.smtz.library.data.vos.BookVO
import com.padcmyanmar.smtz.library.delegate.BookDetailsDelegate
import com.padcmyanmar.smtz.library.delegate.GenreDelegate
import com.padcmyanmar.smtz.library.delegate.LibraryViewsDelegate
import com.padcmyanmar.smtz.library.delegate.SortBooksDelegate
import com.padcmyanmar.smtz.library.mvp.views.ShelfDetailView

interface ShelfDetailPresenter : IBasePresenter, BookDetailsDelegate, LibraryViewsDelegate, GenreDelegate, SortBooksDelegate {
    fun initView(view: ShelfDetailView)
    fun onUiReadyShelfDetail(owner: LifecycleOwner, shelfId: Long)
    fun onTapBack()
    fun onShelfRename(name: String)
    fun onDeleteShelf()
    fun onDeleteBookFromShelf(book: BookVO)
    fun onTapShelfMore()
}