package com.padcmyanmar.smtz.library.persistance.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.padcmyanmar.smtz.library.data.vos.BookVO
import com.padcmyanmar.smtz.library.data.vos.ShelfVO

@Dao
interface ShelfListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertShelf(shelf: ShelfVO)

    @Query("SELECT * FROM shelves")
    fun getAllShelves(): LiveData<List<ShelfVO>>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertShelfList(shelfList: List<ShelfVO>)

    @Query("SELECT * FROM shelves WHERE shelf_id = :id")
    fun getShelfById(id: Long): LiveData<ShelfVO?>?

    @Query("SELECT * FROM shelves WHERE shelf_id = :id")
    fun getShelfByIdOneTime(id: Long): ShelfVO?

    @Query("DELETE FROM shelves WHERE shelf_id = :id")
    fun deleteShelfById(id: Long)

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertSingleBookToShelf(book: BookVO, shelfId: Long)

}