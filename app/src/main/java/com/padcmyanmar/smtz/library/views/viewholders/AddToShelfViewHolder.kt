package com.padcmyanmar.smtz.library.views.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.smtz.library.data.vos.ShelfVO
import com.padcmyanmar.smtz.library.delegate.AddToShelfCheckBoxDelegate
import kotlinx.android.synthetic.main.view_holder_add_to_shelf.view.*
import kotlinx.android.synthetic.main.view_holder_shelf.view.tvShelfName

class AddToShelfViewHolder(itemView: View, delegate: AddToShelfCheckBoxDelegate) : RecyclerView.ViewHolder(itemView) {

    private var mShelf: ShelfVO? = null

    init {
        itemView.cbShelf.setOnClickListener {

            mShelf?.let { delegate.onTapCheckBox(it.shelfId) }
        }
    }

    fun bindData(shelf: ShelfVO) {
        mShelf = shelf

//        Glide.with(itemView.context)
//            .load(book.bookImage)
//            .into(itemView.ivLibraryBookList)
//
        itemView.tvShelfName.text = shelf.shelfName
        itemView.tvBookCountAddToShelf.text = "${shelf.books.count()} books"
    }
}