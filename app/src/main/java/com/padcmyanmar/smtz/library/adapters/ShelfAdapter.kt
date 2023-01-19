package com.padcmyanmar.smtz.library.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.smtz.library.R
import com.padcmyanmar.smtz.library.data.vos.ShelfVO
import com.padcmyanmar.smtz.library.delegate.ShelfDetailDelegate
import com.padcmyanmar.smtz.library.views.viewholders.ShelfViewHolder

class ShelfAdapter(private var mDelegate: ShelfDetailDelegate) : RecyclerView.Adapter<ShelfViewHolder>() {

    private var mShelf: List<ShelfVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShelfViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_shelf, parent, false)
        return ShelfViewHolder(view,mDelegate)
    }

    override fun onBindViewHolder(holder: ShelfViewHolder, position: Int) {
        if (mShelf.isNotEmpty()) {
            holder.bindData(mShelf[position])
        }
    }

    override fun getItemCount(): Int {
        return mShelf.count()
    }

    fun setNewData(shelves: List<ShelfVO>) {
        mShelf = shelves
        notifyDataSetChanged()
    }
}