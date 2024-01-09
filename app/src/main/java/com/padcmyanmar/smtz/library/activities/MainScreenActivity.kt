package com.padcmyanmar.smtz.library.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.padcmyanmar.smtz.library.R
import com.padcmyanmar.smtz.library.delegate.EmptyLibraryDelegate
import com.padcmyanmar.smtz.library.mvp.presenters.MainPresenter
import com.padcmyanmar.smtz.library.mvp.presenters.MainPresenterImpl
import com.padcmyanmar.smtz.library.mvp.views.MainView
import com.padcmyanmar.smtz.library.views.fragments.HomeFragment
import com.padcmyanmar.smtz.library.views.fragments.LibraryFragment
import kotlinx.android.synthetic.main.activity_home_screen.*

class MainScreenActivity : AppCompatActivity(), MainView, EmptyLibraryDelegate {

    private lateinit var mPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        setUpPresenter()

        switchFragment(HomeFragment(this))
        setUpListeners()

        mPresenter.onUiReady(this)
    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this).get(MainPresenterImpl::class.java)
        mPresenter.initView(this)
    }

    private fun setUpListeners() {

        // Bottom Navigation
        bottom_nav.setOnItemSelectedListener { menuItem: MenuItem ->
            when (menuItem.itemId) {
                R.id.action_home -> switchFragment(HomeFragment(this))
                R.id.action_library -> switchFragment(LibraryFragment())
            }
            true
        }

        cardViewSearch.setOnClickListener{
            startActivity(Intent(this, SearchBookActivity::class.java))
        }
    }

    private fun switchFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_container, fragment)
            .commit()
    }

    override fun onTapGoToLibrary() {
        bottom_nav.selectedItemId = R.id.action_library
        switchFragment(LibraryFragment())
    }

    override fun showError(errorString: String) {
        Snackbar.make(window.decorView, errorString, Snackbar.LENGTH_SHORT).show()
    }

}