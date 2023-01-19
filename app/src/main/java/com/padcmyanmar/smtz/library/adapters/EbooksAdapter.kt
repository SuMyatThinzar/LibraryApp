package com.padcmyanmar.smtz.library.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.smtz.library.R
import com.padcmyanmar.smtz.library.data.vos.BookVO
import com.padcmyanmar.smtz.library.delegate.BookDetailsDelegate
import com.padcmyanmar.smtz.library.views.viewholders.EbookViewHolder

class EbooksAdapter(private var mDetailDelegate: BookDetailsDelegate) : RecyclerView.Adapter<EbookViewHolder>() {

    private var mBooks : List<BookVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EbookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_ebook, parent, false)
        return EbookViewHolder(view, mDetailDelegate)
    }

    override fun onBindViewHolder(holder: EbookViewHolder, position: Int) {
        if(mBooks.isNotEmpty()){
            holder.bindData(mBooks[position])
        }
    }

    override fun getItemCount(): Int {
        return mBooks.count()
    }

    fun setNewData(books: List<BookVO>) {
        val bookList : MutableList<BookVO> = mutableListOf()
        books.forEach {
            if (it.bookImage != "" && it.bookImage != null) {
                bookList.add(it)
            }
        }
        mBooks = bookList
        notifyDataSetChanged()
    }
}