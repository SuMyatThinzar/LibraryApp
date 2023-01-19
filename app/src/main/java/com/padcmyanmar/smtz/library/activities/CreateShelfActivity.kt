package com.padcmyanmar.smtz.library.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.padcmyanmar.smtz.library.R
import com.padcmyanmar.smtz.library.mvp.presenters.CreateShelfPresenter
import com.padcmyanmar.smtz.library.mvp.presenters.CreateShelfPresenterImpl
import com.padcmyanmar.smtz.library.mvp.views.CreateShelfView
import kotlinx.android.synthetic.main.activity_create_shelf.*

class CreateShelfActivity : AppCompatActivity(), CreateShelfView {

    private lateinit var mPresenter: CreateShelfPresenter

//    companion object{
//        fun newIntent(context: LibraryFragment): Intent {
//            return Intent(context, CreateShelfActivity::class.java)
//        }
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_shelf)

        setUpPresenter()

        setUpListeners()
        mPresenter.onUiReady(this)
    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this).get(CreateShelfPresenterImpl::class.java)
        mPresenter.initView(this)
    }

    private fun setUpListeners(){
        btnConfirmCreateShelf.setOnClickListener {
            mPresenter.onTapComplete(etShelfName.text.toString())
        }
        etShelfName.setOnEditorActionListener{ text, actionId, _ ->
            return@setOnEditorActionListener when(actionId){
                EditorInfo.IME_ACTION_DONE -> {
                    mPresenter.onTapComplete(text.text.toString())
                    true
                }
                else -> false
            }
        }
    }

    override fun navigateToBackScreen() {
        super.onBackPressed()
    }

    override fun insertShelfComplete() {
        super.onBackPressed()
    }

    override fun showError(errorString: String) {
        Snackbar.make(window.decorView, errorString, Snackbar.LENGTH_SHORT).show()
    }
}