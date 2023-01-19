package com.padcmyanmar.smtz.library.network

import com.padcmyanmar.smtz.library.network.responses.BookResponse
import com.padcmyanmar.smtz.library.network.responses.GoogleBookResponse
import com.padcmyanmar.smtz.library.network.responses.MoreBooksResponse
import com.padcmyanmar.smtz.library.utils.*
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface LibraryApi {

    @GET(API_GET_BOOK_LIST)
    fun getBookList(
        @Query(PARAM_API_KEY) apiKey: String = LIBRARY_API_KEY
    ) : Observable<BookResponse>

    @GET(API_GET_BOOK_MORE_LIST)
    fun getMoreBookList(
        @Query(PARAM_API_KEY) apiKey: String = LIBRARY_API_KEY,
        @Query(PARAM_LIST) list: String
    ) : Observable<MoreBooksResponse>

    @GET(API_GET_GOOGLE_BOOK_LIST)
    fun searchGoogleBooks(
        @Query(PARAM_QUERY) query: String
    ) : Observable<GoogleBookResponse>

}