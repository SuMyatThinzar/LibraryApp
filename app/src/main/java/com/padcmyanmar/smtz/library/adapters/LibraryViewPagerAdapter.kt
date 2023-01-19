package com.padcmyanmar.smtz.library.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.padcmyanmar.smtz.library.delegate.BookDetailsDelegate
import com.padcmyanmar.smtz.library.delegate.LibraryViewsDelegate
import com.padcmyanmar.smtz.library.views.fragments.*
import com.padcmyanmar.smtz.library.views.viewpods.EmptyShelfViewPod

class LibraryViewPagerAdapter(fragmentActivity: LibraryFragment) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> YourBooksFragment()
            else -> YourShelvesFragment()
        }
    }
}