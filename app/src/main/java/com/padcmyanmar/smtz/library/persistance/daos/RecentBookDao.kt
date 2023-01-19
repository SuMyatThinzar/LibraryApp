package com.padcmyanmar.smtz.library.persistance.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.padcmyanmar.smtz.library.data.vos.BookListVO
import com.padcmyanmar.smtz.library.data.vos.BookVO

@Dao
interface RecentBookDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSingleBook(book: BookVO?)

    @Query("SELECT * FROM book WHERE book_list_name = :bookListName")
    fun getRecentBookByListName(bookListName: String): LiveData<List<BookVO>>?

    @Query("SELECT * FROM book")
    fun getAllRecentBooks(): LiveData<List<BookVO>>?



}