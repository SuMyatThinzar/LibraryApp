package com.padcmyanmar.smtz.library.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import com.padcmyanmar.smtz.library.R
import com.padcmyanmar.smtz.library.adapters.LibraryViewPagerAdapter
import com.padcmyanmar.smtz.library.delegate.BookDetailsDelegate
import com.padcmyanmar.smtz.library.delegate.LibraryViewsDelegate
import com.padcmyanmar.smtz.library.mvp.presenters.HomePresenter
import com.padcmyanmar.smtz.library.mvp.presenters.HomePresenterImpl
import com.padcmyanmar.smtz.library.mvp.presenters.LibraryPresenter
import com.padcmyanmar.smtz.library.mvp.presenters.LibraryPresenterImpl
import com.padcmyanmar.smtz.library.mvp.views.LibraryView
import com.padcmyanmar.smtz.library.views.viewpods.EmptyShelfViewPod
import kotlinx.android.synthetic.main.fragment_library.*

class LibraryFragment : Fragment(), LibraryView{

    private lateinit var mPresenter: LibraryPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_library, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpPresenter()

        setUpAdapters()
        setUpTabBar()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this).get(LibraryPresenterImpl::class.java)
        mPresenter.initView(this)
    }

    private fun setUpAdapters() {
        val libraryViewPagerAdapter = LibraryViewPagerAdapter(this)
        viewPagerLibraryBottomNav.adapter = libraryViewPagerAdapter
    }

    private fun setUpTabBar() {
        TabLayoutMediator(tlLibrary, viewPagerLibraryBottomNav) { tab, position ->
            when (position) {
                0 -> tab.text = "Your books"
                else -> tab.text = "Your shelves"
            }
        }.attach()
    }

    override fun showError(errorString: String) {
        Toast.makeText(requireContext(), errorString, Toast.LENGTH_SHORT).show()
    }

}