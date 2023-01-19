package com.padcmyanmar.smtz.library.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.padcmyanmar.smtz.library.R
import com.padcmyanmar.smtz.library.activities.AddToShelfActivity
import com.padcmyanmar.smtz.library.activities.BookDetailsActivity
import com.padcmyanmar.smtz.library.activities.MoreAudiobookActivity
import com.padcmyanmar.smtz.library.activities.MoreEbookActivity
import com.padcmyanmar.smtz.library.adapters.BookListAdapter
import com.padcmyanmar.smtz.library.data.vos.BookListVO
import com.padcmyanmar.smtz.library.data.vos.BookVO
import com.padcmyanmar.smtz.library.mvp.presenters.EbookPresenter
import com.padcmyanmar.smtz.library.mvp.presenters.EbookPresenterImpl
import com.padcmyanmar.smtz.library.mvp.views.EbookView
import kotlinx.android.synthetic.main.bottom_sheet_more_action_home.*
import kotlinx.android.synthetic.main.bottom_sheet_more_action_library.*
import kotlinx.android.synthetic.main.fragment_ebooks.*
import kotlinx.android.synthetic.main.view_holder_list_view.view.*

class EbooksFragment : Fragment(), EbookView {

    private lateinit var mBookListAdapter: BookListAdapter

    private lateinit var mPresenter: EbookPresenter

    private var mBookList: List<BookListVO> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ebooks, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpPresenter()

//        setUpViewPod()
        setUpAdapter()

        mPresenter.onUiReady(this)

        super.onViewCreated(view, savedInstanceState)
    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this).get(EbookPresenterImpl::class.java)
        mPresenter.initView(this)
    }

    private fun setUpAdapter(){
        mBookListAdapter = BookListAdapter(mDetailDelegate = mPresenter, mMoreBooksDelegate = mPresenter)
        rvBookLists.adapter = mBookListAdapter
        rvBookLists.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
    }

    // network data
    override fun showBookLists(bookList: List<BookListVO>) {
        mBookList = bookList
        mBookListAdapter.setNewData(bookList)
    }


    // More Books Screen
    override fun onTapMoreBooks(type: Int, listName: String) {
        when (type) {
            1 -> startActivity(MoreEbookActivity.newIntent(requireContext(), listName))
//            2 -> startActivity(MoreAudiobookActivity.newIntent(requireContext()))
        }
    }

    // Detail Screen
    override fun navigateToBookDetail(book: BookVO) {
        startActivity(BookDetailsActivity.newIntent(requireContext(), book))
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