package com.padcmyanmar.smtz.library.views.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.padcmyanmar.smtz.library.data.vos.ShelfVO
import com.padcmyanmar.smtz.library.delegate.ShelfDetailDelegate
import kotlinx.android.synthetic.main.view_holder_add_to_shelf.view.*
import kotlinx.android.synthetic.main.view_holder_shelf.view.*
import kotlinx.android.synthetic.main.view_holder_shelf.view.tvShelfName

class ShelfViewHolder (itemView: View, private var mDelegate: ShelfDetailDelegate) : RecyclerView.ViewHolder(itemView) {

    private var mShelf: ShelfVO? = null

    init {
        itemView.setOnClickListener {
            mShelf?.let { mDelegate.onTapShelf(it) }
        }
    }

    fun bindData(shelf: ShelfVO) {
        mShelf = shelf

        if(shelf.books.isNotEmpty()){
            Glide.with(itemView.context)
                .load(shelf.books.first().bookImage)
                .into(itemView.ivShelf)
        }
        itemView.tvShelfName.text = shelf.shelfName
        itemView.tvBookCountShelf.text = "${shelf.books.count()} books"
    }
}