package com.padcmyanmar.smtz.library.views.viewholders

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.smtz.library.R
import com.padcmyanmar.smtz.library.data.vos.GenreVO
import com.padcmyanmar.smtz.library.delegate.GenreDelegate
import kotlinx.android.synthetic.main.view_holder_genre_chips.view.*

class GenreChipViewHolder(itemView: View, private var mGenreDelegate: GenreDelegate) : RecyclerView.ViewHolder(itemView) {

    private var mGenre : GenreVO? =null

    init {
        itemView.setOnClickListener{
            mGenre?.let{
                mGenreDelegate.onTapGenre(it.listName?: "")
            }
        }
    }

    fun bindData(genre: GenreVO) {
        mGenre = genre

        itemView.tvMovieDetailGenres.text = genre.listName

        setColor(genre)
    }

    private fun setColor(genre: GenreVO){

        if(genre.isSelected == true){
            itemView.tvMovieDetailGenres.setTextColor(ContextCompat.getColor(itemView.context, R.color.white))
            itemView.tvMovieDetailGenres.background = ContextCompat.getDrawable(
                itemView.context,
                R.drawable.background_genre_chip_selected
            )
        } else {
            itemView.tvMovieDetailGenres.setTextColor(ContextCompat.getColor(itemView.context, R.color.colorSecondaryText))
            itemView.tvMovieDetailGenres.background = ContextCompat.getDrawable(
                itemView.context,
                R.drawable.background_genre_chip
            )
        }
    }
}