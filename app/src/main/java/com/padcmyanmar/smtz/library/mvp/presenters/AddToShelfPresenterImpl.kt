package com.padcmyanmar.smtz.library.mvp.presenters

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.padcmyanmar.smtz.library.data.models.LibraryModel
import com.padcmyanmar.smtz.library.data.models.LibraryModelImpl
import com.padcmyanmar.smtz.library.data.vos.BookVO
import com.padcmyanmar.smtz.library.data.vos.ShelfVO
import com.padcmyanmar.smtz.library.mvp.views.AddToShelfView

class AddToShelfPresenterImpl : ViewModel(), AddToShelfPresenter {

    private var mView: AddToShelfView? = null

    //Model
    private var mLibraryModel: LibraryModel = LibraryModelImpl

    private var mBook: BookVO? = null
    private var mBookListName: String? = null
    private var mShelves: List<ShelfVO> = listOf()
    private var selectedShelves: MutableList<ShelfVO> = mutableListOf()
    private lateinit var lifecycleOwner: LifecycleOwner

    override fun initView(view: AddToShelfView) {
        mView = view
    }

    override fun onUiReadyAddToShelf(owner: LifecycleOwner, book: BookVO, bookListName: String) {
        mBook = book
        mBook?.let { it.bookListName = bookListName }
        lifecycleOwner = owner

        mLibraryModel.getAllShelves()?.observe(owner) { shelves ->
            mShelves = shelves

            mView?.showShelfList(shelves)
        }
    }

    override fun onTapAddToShelfConfirm() {

        selectedShelves.forEach { selectedShelf ->

            mBook?.let { mBook ->
                mShelves.forEach { shelf ->

                    if (shelf.shelfId == selectedShelf.shelfId) {
                        val books = shelf.books
                        books.add(mBook)

                        val newShelf = shelf.copy(books = books)
                        mLibraryModel.insertShelf(newShelf)
                    }
                }
            }

            mView?.navigateToYourShelvesScreen()
        }
    }

    override fun onTapCheckBoxAddShelf(shelfVO: ShelfVO) {
        selectedShelves.add(shelfVO)
    }

    override fun onTapCheckBoxRemoveShelf(shelfVO: ShelfVO) {
        selectedShelves.remove(shelfVO)
    }

    override fun onUiReady(owner: LifecycleOwner) {
    }

}