package com.padcmyanmar.smtz.library.adapters

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.smtz.library.R
import com.padcmyanmar.smtz.library.data.vos.BookListVO
import com.padcmyanmar.smtz.library.data.vos.GenreVO
import com.padcmyanmar.smtz.library.delegate.GenreDelegate
import com.padcmyanmar.smtz.library.views.viewholders.GenreChipViewHolder

class GenreChipAdapter(private var genreDelegate: GenreDelegate) : RecyclerView.Adapter<GenreChipViewHolder>() {

    private var mGenreList: List<GenreVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreChipViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_genre_chips, parent, false)
        return GenreChipViewHolder(view, genreDelegate)
    }

    override fun onBindViewHolder(holder: GenreChipViewHolder, position: Int) {
        if(mGenreList.isNotEmpty()){
            holder.bindData(mGenreList[position])
        }
    }

    override fun getItemCount(): Int {
        return mGenreList.count()
    }

    fun setNewData(genreList: List<GenreVO>){
        mGenreList = genreList
        notifyDataSetChanged()
    }
}