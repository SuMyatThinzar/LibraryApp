package com.padcmyanmar.smtz.library.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.padcmyanmar.smtz.library.R
import com.padcmyanmar.smtz.library.adapters.AddToShelfAdapter
import com.padcmyanmar.smtz.library.data.vos.BookVO
import com.padcmyanmar.smtz.library.data.vos.ShelfVO
import com.padcmyanmar.smtz.library.mvp.presenters.AddToShelfPresenter
import com.padcmyanmar.smtz.library.mvp.presenters.AddToShelfPresenterImpl
import com.padcmyanmar.smtz.library.mvp.views.AddToShelfView
import kotlinx.android.synthetic.main.activity_add_to_shelf.*

class AddToShelfActivity : AppCompatActivity() , AddToShelfView {

    private lateinit var mPresenter: AddToShelfPresenter

    private lateinit var shelfAdapter : AddToShelfAdapter

    private var mBook : BookVO? = null
    private var mBookListName: String? = null

    companion object{

        private const val EXTRA_BOOK = "EXTRA BOOK"
        private const val EXTRA_BOOK_LIST_NAME = "EXTRA BOOK LIST NAME"

        fun newIntent(context: Context, book: BookVO, bookListName: String): Intent {
            val intent = Intent(context, AddToShelfActivity::class.java)

            intent.putExtra(EXTRA_BOOK, book)
            intent.putExtra(EXTRA_BOOK_LIST_NAME, bookListName)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_to_shelf)

        setUpPresenter()
        setUpAdapter()
        setUpListener()

        mBook = intent.getParcelableExtra(EXTRA_BOOK)
        mBookListName = intent.getStringExtra(EXTRA_BOOK_LIST_NAME)

        mBook?.let { book ->
            mBookListName?.let { bookListName ->
                mPresenter.onUiReadyAddToShelf(this, book, bookListName)
            }
        }

    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this).get(AddToShelfPresenterImpl::class.java)
        mPresenter.initView(this)
    }

    private fun setUpAdapter(){
        shelfAdapter = AddToShelfAdapter(mPresenter)
        rvAddToShelf.adapter = shelfAdapter
        rvAddToShelf.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
    }

    private fun setUpListener(){
        btnConfirmAddToShelf.setOnClickListener {
            mPresenter.onTapAddToShelfConfirm()
        }
        btnClose.setOnClickListener {
            finish()
        }
    }

    override fun showShelfList(shelfList: List<ShelfVO>) {
        shelfAdapter.setNewData(shelfList)
    }

    override fun navigateToYourShelvesScreen() {
        finish()
    }

    override fun showError(errorString: String) {
        Snackbar.make(window.decorView, errorString, Snackbar.LENGTH_SHORT).show()
    }

}