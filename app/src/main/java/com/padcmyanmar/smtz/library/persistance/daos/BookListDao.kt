package com.padcmyanmar.smtz.library.persistance.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.padcmyanmar.smtz.library.data.vos.BookListVO

@Dao
interface BookListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllBookLists(bookList: List<BookListVO>)

    @Query("SELECT * FROM bookList")
    fun getAllBookList(): LiveData<List<BookListVO>?>?
}