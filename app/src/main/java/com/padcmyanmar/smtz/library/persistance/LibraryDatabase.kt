package com.padcmyanmar.smtz.library.persistance

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.padcmyanmar.smtz.library.data.vos.BookListVO
import com.padcmyanmar.smtz.library.data.vos.BookVO
import com.padcmyanmar.smtz.library.data.vos.GenreVO
import com.padcmyanmar.smtz.library.data.vos.ShelfVO
import com.padcmyanmar.smtz.library.persistance.daos.BookListDao
import com.padcmyanmar.smtz.library.persistance.daos.RecentBookDao
import com.padcmyanmar.smtz.library.persistance.daos.RecentGenresDao
import com.padcmyanmar.smtz.library.persistance.daos.ShelfListDao

@Database(entities = [BookVO::class, BookListVO::class, GenreVO::class, ShelfVO::class], version = 7, exportSchema = false)
abstract class LibraryDatabase : RoomDatabase() {

    companion object{
        private const val DB_NAME = "THE_LIBRARY"

        var dbInstance: LibraryDatabase? = null

        fun getDBInstance(context: Context): LibraryDatabase? {

            when(dbInstance){
                null ->
                    dbInstance = Room.databaseBuilder(context, LibraryDatabase::class.java, DB_NAME)
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
            }
            return dbInstance
        }
    }

    abstract fun bookListDao(): BookListDao
    abstract fun recentBookDao(): RecentBookDao
    abstract fun recentGenresDao(): RecentGenresDao
    abstract fun shelfListDao(): ShelfListDao
}