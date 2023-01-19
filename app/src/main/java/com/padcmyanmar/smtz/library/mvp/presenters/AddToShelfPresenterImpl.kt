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
        mBook?.let{ it.bookListName = bookListName }
        lifecycleOwner = owner

        mLibraryModel.getAllShelves()?.observe(owner) { shelves ->
            mShelves = shelves

            mView?.showShelfList(shelves)
        }
    }

    override fun onTapAddToShelfConfirm() {

        selectedShelves.forEach { selectedShelf ->

            if (selectedShelf.isChecked) {

                mBook?.let { mBook ->
                    mShelves.forEach { shelf ->

                        if (shelf.shelfId == selectedShelf.shelfId) {

                            val books = shelf.books
                            books.add(mBook)

                            val shelf = shelf.copy(books = books)
                            mLibraryModel.insertShelf(shelf)
//                            Log.d("Ok", shelf.books.toString())
                        }
                    }
                }
            }
            mView?.navigateToYourShelvesScreen()
        }
    }

    override fun onTapCheckBox(shelfId: Long) {
        mShelves.forEach { shelf ->

            selectedShelves.add(shelf.copy(shelf.shelfId, shelf.shelfName, shelf.books))

            selectedShelves.forEach { selectedShelf ->

                if (selectedShelf.shelfId == shelfId) {
                    if (selectedShelf.isChecked == false) {
                        selectedShelf.isChecked = true
                    } else {
                        selectedShelf.isChecked = false
                    }
                }
            }
        }
    }

    override fun onUiReady(owner: LifecycleOwner) {
    }

}