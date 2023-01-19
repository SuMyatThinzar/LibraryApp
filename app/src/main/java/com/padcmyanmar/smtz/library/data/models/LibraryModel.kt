package com.padcmyanmar.smtz.library.data.models

import androidx.lifecycle.LiveData
import com.padcmyanmar.smtz.library.data.vos.BookListVO
import com.padcmyanmar.smtz.library.data.vos.BookVO
import com.padcmyanmar.smtz.library.data.vos.GenreVO
import com.padcmyanmar.smtz.library.data.vos.ShelfVO
import com.padcmyanmar.smtz.library.network.responses.MoreBooksResponse
import io.reactivex.rxjava3.core.Observable

interface LibraryModel {

    fun getBookList(
        onFailure: (String) -> Unit
    ): LiveData<List<BookListVO>?>?

    fun getMoreBookList(
        listName: String,
        onSuccess: (List<BookVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    // 1) insert each book to DB
    fun insertRecentSingleBook(book: BookVO?)

    // 2) get all books from DB
    fun getRecentBooksFromDB() : LiveData<List<BookVO>>?

    // To Bind Genres
    fun getBookListFromDB() : LiveData<List<BookListVO>?>?

    // To Filter by Genres
    fun getRecentBookListByNameFromDB(listName: String) : LiveData<List<BookVO>>?

    // insert Genres from BookList
    fun insertAllRecentGenres(genres: List<GenreVO>)

    // get all genres
    fun getAllRecentGenres(): LiveData<List<GenreVO>>?

    // insert each shelf
    fun insertShelf(shelfVO: ShelfVO)

    // get all shelves from DB
    fun getAllShelves(): LiveData<List<ShelfVO>>?

    fun insertShelfList(shelfList: List<ShelfVO>)

    fun getShelfById(id: Long): LiveData<ShelfVO?>?

    fun getShelfByIdOneTime(
        id: Long,
        onSuccess: (ShelfVO) -> Unit
    )

    fun deleteShelf(id: Long)

    fun searchBooks(
        query: String
    ): Observable<List<BookVO>>
}