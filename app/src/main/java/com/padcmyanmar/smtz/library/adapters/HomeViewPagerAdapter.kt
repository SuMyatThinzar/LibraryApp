package com.padcmyanmar.smtz.library.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.padcmyanmar.smtz.library.delegate.BookDetailsDelegate
import com.padcmyanmar.smtz.library.delegate.MoreBooksDelegate
import com.padcmyanmar.smtz.library.views.fragments.AudiobooksFragments
import com.padcmyanmar.smtz.library.views.fragments.EbooksFragment
import com.padcmyanmar.smtz.library.views.fragments.HomeFragment

class HomeViewPagerAdapter(
    fragmentActivity: HomeFragment,
    ) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> EbooksFragment()
            else -> AudiobooksFragments()
        }
    }
}