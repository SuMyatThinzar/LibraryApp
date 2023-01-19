package com.padcmyanmar.smtz.library.views.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.padcmyanmar.smtz.library.data.vos.BookVO
import com.padcmyanmar.smtz.library.delegate.BookDetailsDelegate
import kotlinx.android.synthetic.main.view_holder_list_view.view.*
import kotlinx.android.synthetic.main.view_holder_search_book.view.*

class SearchBookViewHolder(itemView: View, mDetailDelegate: BookDetailsDelegate) : RecyclerView.ViewHolder(itemView) {

    private var mBook: BookVO? = null

    init {
        itemView.setOnClickListener {
            mBook?.let{ mDetailDelegate.onTapBook(it) }
        }
    }

    fun bindData(book: BookVO) {
        mBook = book

        Glide.with(itemView.context)
            .load(book.bookImage)
            .into(itemView.ivSearchBook)

        itemView.tvSearchBookName.text = book.title
        itemView.tvSearchBookAuthorName.text = book.author

    }
}