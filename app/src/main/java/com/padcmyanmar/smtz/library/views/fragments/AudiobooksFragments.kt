package com.padcmyanmar.smtz.library.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.padcmyanmar.smtz.library.R
import com.padcmyanmar.smtz.library.adapters.BookListAdapter
import com.padcmyanmar.smtz.library.data.vos.BookVO
import com.padcmyanmar.smtz.library.delegate.BookDetailsDelegate

class AudiobooksFragments : Fragment(), BookDetailsDelegate {

//    private lateinit var mMoreAudiobooksViewPod1: MoreAudiobooksViewPod
//    private lateinit var mMoreAudiobooksViewPod2: MoreAudiobooksViewPod
//    private lateinit var mMoreAudiobooksViewPod3: MoreAudiobooksViewPod
//    private lateinit var mMoreAudiobooksViewPod4: MoreAudiobooksViewPod

    private lateinit var mBookListAdapter: BookListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_audiobooks, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        setUpAdapter()

        super.onViewCreated(view, savedInstanceState)
    }

    private fun setUpAdapter() {
//        mBookListAdapter = BookListAdapter(this)
//        rvBookListsAudioBooks.adapter = mBookListAdapter
//        rvBookListsAudioBooks.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
    }

    override fun onTapBook(book: BookVO) {}

    override fun onTapMoreAction(viewType: String, book: BookVO, bookListName: String) {}
}