package com.padcmyanmar.smtz.library.mvp.views

import com.padcmyanmar.smtz.library.data.models.LibraryModel
import com.padcmyanmar.smtz.library.data.vos.BookVO
import com.padcmyanmar.smtz.library.data.vos.GenreVO
import com.padcmyanmar.smtz.library.data.vos.ShelfVO

interface ShelfDetailView : BaseView {
    fun showShelfDetail(shelf: ShelfVO)
    fun showGenreList(genres: MutableList<GenreVO>)
    fun navigateToBookDetail(book: BookVO)
//    fun onTapMoreAction(viewType: String, book: BookVO, bookListName: String)
    fun onTapView()
    fun onTapGenre(genres: List<GenreVO>)
    fun showRecentBookListByName(books: List<BookVO>)
    fun onTapSort()
    fun navigateBack()
    fun showShelfUpdateBottomSheet(mShelf: ShelfVO)
}