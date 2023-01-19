package com.padcmyanmar.smtz.library.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.smtz.library.R
import com.padcmyanmar.smtz.library.data.vos.BookVO
import com.padcmyanmar.smtz.library.delegate.BookDetailsDelegate
import com.padcmyanmar.smtz.library.views.viewholders.ListViewViewHolder
import com.padcmyanmar.smtz.library.views.viewholders.SearchBookViewHolder

class SearchBookAdapter(private var mDetailDelegate: BookDetailsDelegate): RecyclerView.Adapter<SearchBookViewHolder>() {

    private var mbooks : List<BookVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchBookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_search_book, parent, false)
        return SearchBookViewHolder(view, mDetailDelegate)
    }

    override fun onBindViewHolder(holder: SearchBookViewHolder, position: Int) {
        if(mbooks.isNotEmpty()){
            holder.bindData(mbooks[position])
        }
    }

    override fun getItemCount(): Int {
        return mbooks.count()
    }

    fun setNewData(books: List<BookVO>){
        mbooks = books
        notifyDataSetChanged()
    }
}