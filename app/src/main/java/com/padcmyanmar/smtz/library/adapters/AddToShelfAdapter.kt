package com.padcmyanmar.smtz.library.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.smtz.library.R
import com.padcmyanmar.smtz.library.data.vos.ShelfVO
import com.padcmyanmar.smtz.library.delegate.AddToShelfCheckBoxDelegate
import com.padcmyanmar.smtz.library.views.viewholders.AddToShelfViewHolder

class AddToShelfAdapter(private var delegate: AddToShelfCheckBoxDelegate) : RecyclerView.Adapter<AddToShelfViewHolder>() {

    private var mShelves: List<ShelfVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddToShelfViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_add_to_shelf, parent, false)
        return AddToShelfViewHolder(view, delegate)
    }

    override fun onBindViewHolder(holder: AddToShelfViewHolder, position: Int) {
        if (mShelves.isNotEmpty()) {
            holder.bindData(mShelves[position])
        }
    }

    override fun getItemCount(): Int {
        return mShelves.count()
    }

    fun setNewData(shelves: List<ShelfVO>) {
        mShelves = shelves
        notifyDataSetChanged()
    }
}