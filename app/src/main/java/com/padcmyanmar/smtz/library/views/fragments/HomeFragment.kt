package com.padcmyanmar.smtz.library.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.tabs.TabLayoutMediator
import com.padcmyanmar.smtz.library.R
import com.padcmyanmar.smtz.library.activities.AddToShelfActivity
import com.padcmyanmar.smtz.library.activities.BookDetailsActivity
import com.padcmyanmar.smtz.library.adapters.CarouselAdapter
import com.padcmyanmar.smtz.library.adapters.HomeViewPagerAdapter
import com.padcmyanmar.smtz.library.data.vos.BookVO
import com.padcmyanmar.smtz.library.delegate.EmptyLibraryDelegate
import com.padcmyanmar.smtz.library.mvp.presenters.HomePresenter
import com.padcmyanmar.smtz.library.mvp.presenters.HomePresenterImpl
import com.padcmyanmar.smtz.library.mvp.views.HomeView
import com.padcmyanmar.smtz.library.views.viewpods.EmptyLibraryViewPod
import kotlinx.android.synthetic.main.bottom_sheet_more_action_home.*
import kotlinx.android.synthetic.main.bottom_sheet_more_action_library.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_your_shelves.*

class HomeFragment(val delegate: EmptyLibraryDelegate) : Fragment(), HomeView {

    private lateinit var mCarouselAdapter: CarouselAdapter
    private lateinit var homeViewPagerAdapter: HomeViewPagerAdapter
    private lateinit var mEmptyViewPod: EmptyLibraryViewPod

    private lateinit var mPresenter: HomePresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        setUpPresenter()

        setUpAdapters()
        setUpTabBar()
//        setUpDelegate(mMoreDelegate)

        mPresenter.onUiReady(this)

        super.onViewCreated(view, savedInstanceState)
    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this).get(HomePresenterImpl::class.java)
        mPresenter.initView(this)
    }


    private fun setUpAdapters() {
        // setUp Empty ViewPod
        mEmptyViewPod = vpEmptyCarousel as EmptyLibraryViewPod
        mEmptyViewPod.setUpDelegate(delegate)

        // setUp Ebook and Audiobook Fragments
        homeViewPagerAdapter = HomeViewPagerAdapter(this)
        viewPagerHomeBottomNav.adapter = homeViewPagerAdapter
        viewPagerHomeBottomNav.isUserInputEnabled = false       // Disable swiping between Fragments (for nested horizontal scroll)

        mCarouselAdapter = CarouselAdapter(mDetailDelegate = mPresenter)
        carouselRecyclerview.adapter = mCarouselAdapter
        carouselRecyclerview.apply {
            set3DItem(false)
            setAlpha(true)
            setInfinite(false)
            setIntervalRatio(0.85f)
        }
    }

    private fun setUpTabBar() {
        TabLayoutMediator(tlHomeScreen, viewPagerHomeBottomNav) { tab, position ->
            when (position) {
                0 -> tab.text = "Ebooks"
                else -> tab.text = "AudioBooks"
            }
        }.attach()
    }

    // Detail Screen
    override fun navigateToBookDetail(book: BookVO) {
        startActivity(BookDetailsActivity.newIntent(requireContext(), book))
    }

    override fun showRecentBookList(books: List<BookVO>) {
//        if(books.isEmpty()){
//            vpEmptyCarousel.visibility = View.VISIBLE
//        }else{
//            vpEmptyCarousel.visibility = View.INVISIBLE
//        }
        mCarouselAdapter.setNewData(books)
    }

    override fun showEmptyView() {
        vpEmptyCarousel.visibility = View.VISIBLE
    }

    override fun hideEmptyView() {
        vpEmptyCarousel.visibility = View.GONE
    }

    // bottom sheet
    override fun onTapMoreAction(viewType: String, book: BookVO, bookListName: String) {

        when (viewType) {
            "Home" -> {
                val dialog = setUpBottomSheet(R.layout.bottom_sheet_more_action_home)

                dialog.tvEbookNameBottomSheet.text = book.title
                dialog.tvEbookAuthorNameBottomSheet.text = book.author

                Glide.with(this)
                    .load(book.bookImage)
                    .into(dialog.ivEbookBottomSheet)

                dialog.llAddtoShelves.setOnClickListener {
                    startActivity(AddToShelfActivity.newIntent(requireContext(), book, bookListName))
                    dialog.dismiss()
                }
            }
            "Library" -> {
                val dialog = setUpBottomSheet(R.layout.bottom_sheet_more_action_library)

                dialog.tvBookNameBottomSheetLibrary.text = book.title
                dialog.tvBookAuthorNameBottomSheetLibrary.text = book.author

                Glide.with(this)
                    .load(book.bookImage)
                    .into(dialog.ivBookBottomSheetLibrary)

                dialog.llAddtoShelvesListView.setOnClickListener {
                    startActivity(AddToShelfActivity.newIntent(requireContext(), book, bookListName))
                    dialog.dismiss()
                }
            }
        }
    }

    private fun setUpBottomSheet(view: Int): BottomSheetDialog {

        val dialog = BottomSheetDialog(requireContext())
        dialog.setContentView(view)
        dialog.show()

        return dialog

    }

    override fun showError(errorString: String) {
        Toast.makeText(requireContext(), errorString, Toast.LENGTH_SHORT).show()
    }

}