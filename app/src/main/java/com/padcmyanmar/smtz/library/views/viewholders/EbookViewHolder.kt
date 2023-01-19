package com.padcmyanmar.smtz.library.views.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.padcmyanmar.smtz.library.R
import com.padcmyanmar.smtz.library.data.vos.BookVO
import com.padcmyanmar.smtz.library.delegate.BookDetailsDelegate
import kotlinx.android.synthetic.main.view_holder_ebook.view.*

class EbookViewHolder(itemView: View, mDetailDelegate: BookDetailsDelegate) : RecyclerView.ViewHolder(itemView) {

    private var mBook: BookVO? = null

    init {
        // Detail
        itemView.setOnClickListener {
            mBook?.let { mDetailDelegate.onTapBook(it) }

        }
        // More Action
        itemView.btnMoreActionEbooks.setOnClickListener {
            mBook?.let { book->
                book.bookListName?.let { bookListName ->
                    mDetailDelegate.onTapMoreAction("Home", book, bookListName)
                }
            }
        }
    }

    fun bindData(book: BookVO) {
        mBook = book

        if(book.bookImage == "" || book.bookImage == null){
            itemView.ivEbook.setImageResource(R.drawable.book_cover)
        } else {
            Glide.with(itemView.context)
                .load(book.bookImage)
                .into(itemView.ivEbook)
        }
        itemView.tvEbookName.text = book.title
    }

}