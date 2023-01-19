package com.padcmyanmar.smtz.library.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.padcmyanmar.smtz.library.R
import com.padcmyanmar.smtz.library.adapters.AudiobooksAdapter
import com.padcmyanmar.smtz.library.data.vos.BookVO
import com.padcmyanmar.smtz.library.delegate.BookDetailsDelegate
import kotlinx.android.synthetic.main.activity_more_audiobook.*
import kotlinx.android.synthetic.main.activity_more_audiobook.btnBack

class MoreAudiobookActivity : AppCompatActivity(), BookDetailsDelegate {

    lateinit var mAudiobooksAdapter: AudiobooksAdapter

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, MoreAudiobookActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_more_audiobook)

        setUpViewPod()
        setUpListeners()
    }

    private fun setUpViewPod() {
        mAudiobooksAdapter = AudiobooksAdapter()
        rvMoreAudiobooksSecondScreen.adapter = mAudiobooksAdapter
        rvMoreAudiobooksSecondScreen.layoutManager = GridLayoutManager(applicationContext, 2)
    }

    override fun onTapBook(book: BookVO) {
        startActivity(BookDetailsActivity.newIntent(this, book))
    }

    override fun onTapMoreAction(viewType: String, book: BookVO, bookListName: String) {
        Toast.makeText(applicationContext, "onTapMoreAction", Toast.LENGTH_SHORT).show()
    }

    private fun setUpListeners(){
        btnBack.setOnClickListener {
            super.onBackPressed()
        }
    }
}