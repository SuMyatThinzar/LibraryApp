package com.padcmyanmar.smtz.library.views.viewholders

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.smtz.library.adapters.EbooksAdapter
import com.padcmyanmar.smtz.library.data.vos.BookListVO
import com.padcmyanmar.smtz.library.data.vos.BookVO
import com.padcmyanmar.smtz.library.delegate.BookDetailsDelegate
import com.padcmyanmar.smtz.library.delegate.MoreBooksDelegate
import kotlinx.android.synthetic.main.view_holder_ebook_list.view.*

class BookListViewHolder(itemView: View, mDetailDelegate: BookDetailsDelegate, private var mMoreBooksDelegate: MoreBooksDelegate) : RecyclerView.ViewHolder(itemView) {

    private var bookListVO: BookListVO? = null
    private var mBooks: List<BookVO> = listOf()

    private var books = itemView.rvMoreEbooks
    private var mEbooksAdapter: EbooksAdapter = EbooksAdapter(mDetailDelegate)   // Child Adapter

    init {
        books.adapter = mEbooksAdapter
        books.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL,false)

        itemView.btnMoreEbooks.setOnClickListener {
            bookListVO?.listName?.let{ listName ->
                mMoreBooksDelegate.onTapMoreBooks(1, listName)
            }
        }

    }

    fun bindData(bookList: BookListVO) {
        bookListVO = bookList

        itemView.tvMore.text = bookList.displayName

        bookList.books?.let {
            mBooks = it
        }

        mEbooksAdapter.setNewData(mBooks)
    }
}