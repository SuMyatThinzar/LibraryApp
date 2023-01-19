package com.padcmyanmar.smtz.library.views.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.padcmyanmar.smtz.library.data.vos.BookVO
import com.padcmyanmar.smtz.library.delegate.BookDetailsDelegate
import kotlinx.android.synthetic.main.view_holder_carousel.view.*
import kotlinx.android.synthetic.main.view_holder_ebook.view.*

class CarouselViewHolder(itemView: View, mDetailDelegate: BookDetailsDelegate) : RecyclerView.ViewHolder(itemView) {

    private var mBook: BookVO? = null

    init {
        itemView.setOnClickListener {
            mBook?.let { mDetailDelegate.onTapBook(it) }
        }
        itemView.btnMoreActionCarousel.setOnClickListener {
            mBook?.let { book->
                book.bookListName?.let { bookListName ->
                    mDetailDelegate.onTapMoreAction("Library", book, bookListName)
                }
            }
        }
    }

    fun bindData(book: BookVO) {
        mBook = book

        Glide.with(itemView.context)
            .load(book.bookImage)
            .into(itemView.ivCarousel)

    }
}