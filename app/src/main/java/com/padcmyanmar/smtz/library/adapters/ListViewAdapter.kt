package com.padcmyanmar.smtz.library.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.smtz.library.R
import com.padcmyanmar.smtz.library.data.vos.BookVO
import com.padcmyanmar.smtz.library.delegate.BookDetailsDelegate
import com.padcmyanmar.smtz.library.views.viewholders.ListViewViewHolder

class ListViewAdapter(private var mDetailDelegate: BookDetailsDelegate): RecyclerView.Adapter<ListViewViewHolder>() {

    private var mbooks : List<BookVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_list_view, parent, false)
        return ListViewViewHolder(view, mDetailDelegate)
    }

    override fun onBindViewHolder(holder: ListViewViewHolder, position: Int) {
        if(mbooks.isNotEmpty()){
            holder.bindData(mbooks[position])
        }
    }

    override fun getItemCount(): Int {
        return mbooks.count()
    }

    fun setNewData(books: List<BookVO>){
        mbooks = books.reversed()
        notifyDataSetChanged()
    }
}