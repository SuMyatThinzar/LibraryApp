package com.padcmyanmar.smtz.library.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.padcmyanmar.smtz.library.data.models.LibraryModel
import com.padcmyanmar.smtz.library.data.models.LibraryModelImpl
import com.padcmyanmar.smtz.library.data.vos.BookVO
import com.padcmyanmar.smtz.library.data.vos.GenreVO
import com.padcmyanmar.smtz.library.mvp.views.YourBookView

class YourBookPresenterImpl : ViewModel(), YourBookPresenter {

    var mView: YourBookView? = null

    //Model
    private var mLibraryModel : LibraryModel = LibraryModelImpl

    private var mSelectedListName : String = ""
    private var mGenres : MutableList<GenreVO> = mutableListOf()
    private lateinit var lifecycleOwner : LifecycleOwner

    override fun initView(view: YourBookView) {
        mView = view
    }

    override fun onUiReady(owner: LifecycleOwner) {
        lifecycleOwner = owner

        // Recent Books
//        mLibraryModel.getRecentBooksFromDB()
//            ?.observe(owner){ books->
//                mView?.showRecentBookList(books)
//
//                var mBookListName = ""
//                // Genres from recent books
//                books.forEach { book->
//                    if(book.bookListName != mBookListName){
//                        mBookListName = book.bookListName ?: ""
//                        mGenres.add(GenreVO(book.bookListName?:"",false))
//                    }
//                }
//                mView?.showGenreList(mGenres)
//            }

        mLibraryModel.getRecentBooksFromDB()
            ?.observe(owner){ books ->
                mView?.showRecentBookList(books)

                books.forEach { book ->
                    if(mSelectedListName == book.bookListName){
                        mGenres.add(GenreVO(listName = book.bookListName!!,true))
                    } else {
                        mGenres.add(GenreVO(listName = book.bookListName!!))
                    }
                }
                mLibraryModel.insertAllRecentGenres(mGenres)

                mLibraryModel.getAllRecentGenres()
                    ?.observe(owner){ genres ->
                        mView?.showGenreList(genres)
                    }
            }
    }

    override fun onTapBook(book: BookVO) {
        mView?.navigateToBookDetail(book)
    }

    override fun onTapMoreAction(viewType: String, book: BookVO, bookListName: String) {
        mView?.onTapMoreAction(viewType, book, bookListName)
    }

    override fun onTapView() {
        mView?.onTapView()
    }

    // change genre background and set new data to genreList
    override fun onTapGenre(listName: String) {
        mSelectedListName = listName

        mLibraryModel.getAllRecentGenres()
            ?.observe(lifecycleOwner){ genreList->

                genreList.forEach { genre ->
                    if(genre.listName == listName){

                        genre.isSelected = genre.isSelected == false

                        // Recent Books By ListName
                        mLibraryModel.getRecentBookListByNameFromDB(genre.listName)
                            ?.observe(lifecycleOwner){ books->
                                mView?.showRecentBookListByName(books)
                            }
                    }
                }
                mView?.onTapGenre(genreList)
            }
    }

    override fun onTapSort() {
        mView?.onTapSort()
    }
}