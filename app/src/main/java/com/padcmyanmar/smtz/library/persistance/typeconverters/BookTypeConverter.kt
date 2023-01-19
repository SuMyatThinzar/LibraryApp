package com.padcmyanmar.smtz.library.persistance.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padcmyanmar.smtz.library.data.vos.BookVO

class BookTypeConverter {

    @TypeConverter
    fun toString(book : List<BookVO>?) : String{
        return Gson().toJson(book)
    }
    @TypeConverter
    fun toBookVO(commentListStr: String) : List<BookVO>? {
        val bookVOType = object : TypeToken<List<BookVO>?>() {}.type
        return Gson().fromJson(commentListStr,bookVOType)
    }
}