package com.padcmyanmar.smtz.library.data.models

import android.content.Context
import com.padcmyanmar.smtz.library.network.LibraryApi
import com.padcmyanmar.smtz.library.persistance.LibraryDatabase
import com.padcmyanmar.smtz.library.utils.BASE_URL
import com.padcmyanmar.smtz.library.utils.GOOGLE_BASE_URL
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

abstract class BaseModel {

    protected var mLibraryApi: LibraryApi
    protected var mGoogleApi: LibraryApi
    protected var mLibraryDatabase: LibraryDatabase? = null

    init {

        val mOkHttpClient = OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(mOkHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())                //Observable ko return pyn loh ya mhr
            .build()

        val retrofitGoogleApi = Retrofit.Builder()
            .baseUrl(GOOGLE_BASE_URL)
            .client(mOkHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())                //Observable ko return pyn loh ya mhr
            .build()

        mLibraryApi = retrofit.create(LibraryApi::class.java)
        mGoogleApi = retrofitGoogleApi.create(LibraryApi::class.java)
    }

    fun initDatabase(context: Context) {
        mLibraryDatabase = LibraryDatabase.getDBInstance(context)
    }

}