package com.padcmyanmar.smtz.library.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.smtz.library.R
import com.padcmyanmar.smtz.library.data.vos.BookListVO
import com.padcmyanmar.smtz.library.delegate.BookDetailsDelegate
import com.padcmyanmar.smtz.library.delegate.MoreBooksDelegate
import com.padcmyanmar.smtz.library.views.viewholders.BookListViewHolder

class BookListAdapter(private var mDetailDelegate: BookDetailsDelegate, private var mMoreBooksDelegate: MoreBooksDelegate) : RecyclerView.Adapter<BookListViewHolder>() {

    private var mBookList : List<BookListVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_ebook_list, parent, false)
        return BookListViewHolder(view, mDetailDelegate, mMoreBooksDelegate)
    }

    override fun onBindViewHolder(holder: BookListViewHolder, position: Int) {
        if(mBookList.isNotEmpty()){
            holder.bindData(mBookList[position])
        }
    }

    override fun getItemCount(): Int {
        return mBookList.count()
    }

    fun setNewData(bookList: List<BookListVO>) {
        mBookList = bookList
        notifyDataSetChanged()
    }
}