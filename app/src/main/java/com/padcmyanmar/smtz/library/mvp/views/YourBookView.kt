package com.padcmyanmar.smtz.library.mvp.views

import com.padcmyanmar.smtz.library.data.models.LibraryModel
import com.padcmyanmar.smtz.library.data.vos.BookListVO
import com.padcmyanmar.smtz.library.data.vos.BookVO
import com.padcmyanmar.smtz.library.data.vos.GenreVO

interface YourBookView : BaseView {
    fun navigateToBookDetail(book: BookVO)
    fun onTapMoreAction(viewType: String, book: BookVO, bookListName: String)
    fun onTapView()
    fun showGenreList(bookList: List<GenreVO>)
    fun showRecentBookList(books: List<BookVO>)
    fun showRecentBookListByName(books: List<BookVO>)
    fun onTapGenre(genres: List<GenreVO>)
    fun onTapSort()
}