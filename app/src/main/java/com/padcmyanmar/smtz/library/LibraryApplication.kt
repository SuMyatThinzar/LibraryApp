package com.padcmyanmar.smtz.library

import android.app.Application
import com.padcmyanmar.smtz.library.data.models.LibraryModelImpl

class LibraryApplication  : Application() {
    override fun onCreate() {
        super.onCreate()

//        val mLibraryModel : LibraryModel = LibraryModelImpl
        LibraryModelImpl.initDatabase(applicationContext)
    }
}