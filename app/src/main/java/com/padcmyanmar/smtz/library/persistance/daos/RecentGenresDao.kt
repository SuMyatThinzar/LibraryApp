package com.padcmyanmar.smtz.library.persistance.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.padcmyanmar.smtz.library.data.vos.BookListVO
import com.padcmyanmar.smtz.library.data.vos.BookVO
import com.padcmyanmar.smtz.library.data.vos.GenreVO

@Dao
interface RecentGenresDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSingleGenre(genre: GenreVO?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllRecentGenres(genres: List<GenreVO>)

    @Query("SELECT * FROM genre")
    fun getAllRecentGenres(): LiveData<List<GenreVO>>?

}