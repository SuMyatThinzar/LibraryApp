package com.padcmyanmar.smtz.library.data.models

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import com.padcmyanmar.smtz.library.R
import com.padcmyanmar.smtz.library.data.vos.BookListVO
import com.padcmyanmar.smtz.library.data.vos.BookVO
import com.padcmyanmar.smtz.library.data.vos.GenreVO
import com.padcmyanmar.smtz.library.data.vos.ShelfVO
import com.padcmyanmar.smtz.library.network.responses.MoreBooksResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

object LibraryModelImpl : LibraryModel, BaseModel() {

    @SuppressLint("CheckResult")
    override fun getBookList(onFailure: (String) -> Unit): LiveData<List<BookListVO>?>? {
        mLibraryApi.getBookList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                response.results?.lists?.forEach { bookList ->
                    bookList.books?.forEach { book ->
                        book.bookListName = bookList.listName.toString()
                    }
                }
                response.results?.lists?.let { bookList ->
                    mLibraryDatabase?.bookListDao()?.insertAllBookLists(bookList = bookList)
                }
            }, {
                    onFailure(it.localizedMessage ?: "")
                })
        return mLibraryDatabase?.bookListDao()?.getAllBookList()
    }

    @SuppressLint("CheckResult")
    override fun getMoreBookList(
        listName: String,
        onSuccess: (List<BookVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {

        mLibraryApi.getMoreBookList(list = listName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->

                val list : MutableList<BookVO> = mutableListOf()

                response.results.forEach { result ->

                    result.book_details.forEach { bookDetail ->
//                        bookDetail.bookImage = R.drawable.book_cover
                        list.add(bookDetail)

                    }
                }

                 onSuccess(list)

            }, {
                onFailure(it.localizedMessage ?: "")
            })
    }

    // 3) PresenterImpl -> onTapBook
    override fun insertRecentSingleBook(book: BookVO?) {
        mLibraryDatabase?.recentBookDao()?.insertSingleBook(book)
    }

    // 4) PresenterImpl -> onUiReady observe
    override fun getRecentBooksFromDB(): LiveData<List<BookVO>>? {
        return mLibraryDatabase?.recentBookDao()?.getAllRecentBooks()
    }

    override fun getBookListFromDB(): LiveData<List<BookListVO>?>? {
        return mLibraryDatabase?.bookListDao()?.getAllBookList()
    }


    // YourBookPresenterImpl's -> onTapGenre -> getAllRecentGenres() -> listName==listName
    override fun getRecentBookListByNameFromDB(listName: String): LiveData<List<BookVO>>? {
        return mLibraryDatabase?.recentBookDao()?.getRecentBookByListName(listName)
    }

    // insert in SortingAndDisplayViewPod -> onTapGenre -> isSelected==true
    override fun insertAllRecentGenres(genres: List<GenreVO>) {
        mLibraryDatabase?.recentGenresDao()?.insertAllRecentGenres(genres)
    }

    // YourBookPresenterImpl's -> onTapGenre -> isSelected
    override fun getAllRecentGenres(): LiveData<List<GenreVO>>? {
        return mLibraryDatabase?.recentGenresDao()?.getAllRecentGenres()
    }

    override fun insertShelf(shelfVO: ShelfVO) {
        mLibraryDatabase?.shelfListDao()?.insertShelf(shelfVO)
    }

    override fun getAllShelves(): LiveData<List<ShelfVO>>? {
        return mLibraryDatabase?.shelfListDao()?.getAllShelves()
    }

    override fun insertShelfList(shelfList: List<ShelfVO>) {
        mLibraryDatabase?.shelfListDao()?.insertShelfList(shelfList)
    }

    override fun getShelfById(id: Long): LiveData<ShelfVO?>? {
        return mLibraryDatabase?.shelfListDao()?.getShelfById(id)
    }

    override fun getShelfByIdOneTime(
        id: Long,
        onSuccess: (ShelfVO) -> Unit
    ) {
        mLibraryDatabase?.shelfListDao()?.getShelfByIdOneTime(id)?.let { onSuccess(it) }
    }


    override fun deleteShelf(id: Long) {
        mLibraryDatabase?.shelfListDao()?.deleteShelfById(id)
    }

    override fun searchBooks(query: String): Observable<List<BookVO>> {
        return mGoogleApi
            .searchGoogleBooks(query = query)
            .map { response ->

                response.items?.map { gBook ->

                    gBook.googleBookToBookVO()

                } ?: listOf()
            }
            .onErrorResumeNext { Observable.just(listOf()) }
            .subscribeOn(Schedulers.io())
    }
}