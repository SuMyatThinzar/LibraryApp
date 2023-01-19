package com.padcmyanmar.smtz.library.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.smtz.library.R
import com.padcmyanmar.smtz.library.views.viewholders.AudiobooksViewHolder

class AudiobooksAdapter: RecyclerView.Adapter<AudiobooksViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AudiobooksViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_audiobook, parent, false)
        return AudiobooksViewHolder(view)
    }

    override fun onBindViewHolder(holder: AudiobooksViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 7
    }
}