package com.padcmyanmar.smtz.library.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.padcmyanmar.smtz.library.R
import com.padcmyanmar.smtz.library.data.vos.BookVO
import kotlinx.android.synthetic.main.activity_book_details.*
import kotlinx.android.synthetic.main.view_holder_ebook.view.*

class BookDetailsActivity : AppCompatActivity() {

    private var mBook : BookVO? = null

    companion object{
        private const val EXTRA_BOOK = "EXTRA BOOK"

        fun newIntent(context: Context, book: BookVO): Intent {
            val intent = Intent(context, BookDetailsActivity::class.java)
            intent.putExtra(EXTRA_BOOK, book)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_details)

        mBook = intent.getParcelableExtra(EXTRA_BOOK)
        mBook?.let{
            bindData(it)
        }
        setUpListener()

    }

    private fun setUpListener(){
        btnBack.setOnClickListener {
            super.onBackPressed()
        }
    }

    private fun bindData(book: BookVO) {

        Glide.with(this)
            .load(book.bookImage)
            .into(ivBookImage)

        tvBookNameBookDetail.text = book.title
        tvBookAuthorNameBookDetail.text = book.author
    }
}