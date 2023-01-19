package com.padcmyanmar.smtz.library.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.jakewharton.rxbinding4.widget.textChanges
import com.padcmyanmar.smtz.library.R
import com.padcmyanmar.smtz.library.adapters.SearchBookAdapter
import com.padcmyanmar.smtz.library.data.models.LibraryModel
import com.padcmyanmar.smtz.library.data.models.LibraryModelImpl
import com.padcmyanmar.smtz.library.data.vos.BookVO
import com.padcmyanmar.smtz.library.mvp.presenters.SearchBookPresenter
import com.padcmyanmar.smtz.library.mvp.presenters.SearchBookPresenterImpl
import com.padcmyanmar.smtz.library.mvp.views.SearchBookView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_search_book.*
import java.util.concurrent.TimeUnit

class SearchBookActivity : AppCompatActivity(), SearchBookView {

    private lateinit var mPresenter: SearchBookPresenter
    private lateinit var mSearchBookAdapter: SearchBookAdapter

    //Model
    private var mLibraryModel: LibraryModel = LibraryModelImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_book)

        setUpPresenter()

        setUpMovieAdapter()
        setUpListener()

        mPresenter.onUiReady(this)
    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this)[SearchBookPresenterImpl::class.java]
        mPresenter.initView(this)
    }

    private fun setUpMovieAdapter() {
        mSearchBookAdapter = SearchBookAdapter(mPresenter)
        rvSearchBookList.adapter = mSearchBookAdapter
//        rvSearchBookList.layoutManager = GridLayoutManager(this,2)
        rvSearchBookList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun setUpListener() {
        etSearch.textChanges()
            .debounce(500L, TimeUnit.MILLISECONDS)
            .flatMap {
                mLibraryModel.searchBooks(query = it.toString())
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                mSearchBookAdapter.setNewData(it)
            }, {
                Snackbar.make(window.decorView, it.toString(), Snackbar.LENGTH_SHORT).show()
            })
    }

    override fun navigateToBookDetail(book: BookVO) {
        startActivity(BookDetailsActivity.newIntent(this, book))
    }

    override fun showError(errorString: String) {
        Snackbar.make(window.decorView, errorString, Snackbar.LENGTH_SHORT).show()
    }

}