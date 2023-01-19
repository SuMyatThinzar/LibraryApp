package com.padcmyanmar.smtz.library.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.padcmyanmar.smtz.library.R
import com.padcmyanmar.smtz.library.adapters.EbooksAdapter
import com.padcmyanmar.smtz.library.adapters.LargeGridViewAdapter
import com.padcmyanmar.smtz.library.data.models.LibraryModel
import com.padcmyanmar.smtz.library.data.models.LibraryModelImpl
import com.padcmyanmar.smtz.library.data.vos.BookVO
import com.padcmyanmar.smtz.library.delegate.BookDetailsDelegate
import kotlinx.android.synthetic.main.activity_more_ebook.*
import kotlinx.android.synthetic.main.activity_more_ebook.btnBack

class MoreEbookActivity : AppCompatActivity(), BookDetailsDelegate {

    lateinit var mEbooksAdapter: LargeGridViewAdapter
    lateinit var mListName: String

    //Model
    private var mLibraryModel: LibraryModel = LibraryModelImpl

    companion object{

        private const val EXTRA_LIST_NAME = "EXTRA LIST NAME"

        fun newIntent(context: Context, listName: String): Intent {
            val intent = Intent(context, MoreEbookActivity::class.java)
            intent.putExtra(EXTRA_LIST_NAME, listName)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_more_ebook)

        mListName = intent.getStringExtra(EXTRA_LIST_NAME).toString()

        setUpAdapter()
        setUpListeners()
    }

    private fun setUpAdapter() {
        mEbooksAdapter = LargeGridViewAdapter(this)
        rvMoreEbooksSecondScreen.adapter = mEbooksAdapter
        rvMoreEbooksSecondScreen.layoutManager = GridLayoutManager(applicationContext, 2)

        requestData(mListName)
    }

    private fun requestData(listName: String){
        mLibraryModel.getMoreBookList(
            listName = listName,
            onSuccess = { books->
                mEbooksAdapter.setNewData(books)
            },
            onFailure = {
                Snackbar.make(window.decorView, it, Snackbar.LENGTH_SHORT).show()
            }
        )
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