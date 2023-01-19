package com.padcmyanmar.smtz.library.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.padcmyanmar.smtz.library.data.models.LibraryModel
import com.padcmyanmar.smtz.library.data.models.LibraryModelImpl
import com.padcmyanmar.smtz.library.data.vos.BookVO
import com.padcmyanmar.smtz.library.data.vos.GenreVO
import com.padcmyanmar.smtz.library.data.vos.ShelfVO
import com.padcmyanmar.smtz.library.mvp.views.ShelfDetailView

class ShelfDetailPresenterImpl : ViewModel(), ShelfDetailPresenter {

    private var mView: ShelfDetailView? = null

    //Model
    private var mLibraryModel: LibraryModel = LibraryModelImpl

    private var mGenres: MutableList<GenreVO> = mutableListOf()
    private lateinit var lifecycleOwner: LifecycleOwner
    private var mSelectedListName: String = ""
    private var mShelf: ShelfVO? = null
    private var mBookListOfShelf: MutableList<BookVO> = mutableListOf()

    override fun initView(view: ShelfDetailView) {
        mView = view
    }

    override fun onUiReadyShelfDetail(owner: LifecycleOwner, shelfId: Long) {
        lifecycleOwner = owner

//        mLibraryModel.getShelfById(id = shelfId)
//            ?.observe(owner) {
//                it?.let { shelf ->
//                    mShelf = shelf
//
//                    if (shelf.books.isNotEmpty()) {
//
//                        shelf.books.forEach { shelfBook ->
//
//                            if (mSelectedListName == shelfBook.bookListName) {
//                                mGenres.add(GenreVO(listName = shelfBook.bookListName!!, true))
//                            } else {
//                                mGenres.add(GenreVO(listName = shelfBook.bookListName!!))
//                            }
//                            mBookListOfShelf.add(shelfBook)
//                        }
//                    }
//                    mView?.showGenreList(mGenres.toMutableList())
//                    mView?.showShelfDetail(shelf)
//                }
//            }

        mLibraryModel.getShelfByIdOneTime(
            id = shelfId,
            onSuccess = { shelf ->
                mShelf = shelf

                if (shelf.books.isNotEmpty()) {

                    // to filter duplicate genre/bookListName
                    val bookList : MutableSet<String> = mutableSetOf()

                    shelf.books.forEach { shelfBook ->
                        val book = shelfBook.bookListName
                        if (book != null) {
                            bookList.add(book)
                        }
                        // show all books of this shelf
                        mBookListOfShelf.add(shelfBook)
                    }

                    // genre selection
                    bookList.forEach { shelfBook ->

                        if (mSelectedListName == shelfBook) {
                            mGenres.add(GenreVO(listName = shelfBook, true))
                        } else {
                            mGenres.add(GenreVO(listName = shelfBook))
                        }
                    }
                }
                mView?.showGenreList(mGenres)
                mView?.showShelfDetail(shelf)

            }
        )
    }

    override fun onTapBack() {
        mView?.navigateBack()
    }

    override fun onShelfRename(name: String) {
        val shelf = mShelf?.copy(shelfName = name)
        if (name.isNotEmpty()) {
            shelf?.let {
                mLibraryModel.insertShelf(it)
            }
        } else {
            mView?.showError("Shelf name can't be empty")
        }
    }

    override fun onDeleteShelf() {
        mShelf.let {
            if (it != null) {
                mLibraryModel.deleteShelf(it.shelfId)
                mView?.navigateBack()
            }
        }
    }

    override fun onDeleteBookFromShelf(book: BookVO) {
        val bookList = mShelf?.books?.filter { it.title != book.title } ?: listOf()
        val shelf = mShelf?.copy(books = bookList.toMutableList())
        shelf?.let {
            mLibraryModel.insertShelf(shelf)
        }

    }

    override fun onTapShelfMore() {
        mShelf?.let {
            mView?.showShelfUpdateBottomSheet(it)
        }
    }

    override fun onTapBook(book: BookVO) {
        mView?.navigateToBookDetail(book)
    }

    override fun onTapMoreAction(viewType: String, book: BookVO, bookListName: String) {
//        mView?.onTapMoreAction(viewType, book, bookListName)
    }

    override fun onTapView() {
        mView?.onTapView()
    }

    override fun onTapGenre(listName: String) {
        mSelectedListName = listName

        if (mGenres.isNotEmpty()) {
            mGenres.forEach { genre ->

                if (genre.listName == listName) {
                    genre.isSelected = genre.isSelected == false

                } else {
                    genre.isSelected = false
                }

                val bookListTemp: MutableList<BookVO> = mutableListOf()

                mBookListOfShelf.forEach { book ->
                    if (book.bookListName == listName) {

                        bookListTemp.add(book)
                        mView?.showRecentBookListByName(bookListTemp)
                    }
                }

            }
            mView?.onTapGenre(mGenres.toMutableList())
        }
    }

    override fun onTapSort() {
        mView?.onTapSort()
    }

    override fun onUiReady(owner: LifecycleOwner) {
    }

}