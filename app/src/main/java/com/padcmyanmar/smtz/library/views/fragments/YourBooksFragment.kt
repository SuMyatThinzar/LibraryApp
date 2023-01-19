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
import com.padcmyanmar.smtz.library.R
import com.padcmyanmar.smtz.library.activities.AddToShelfActivity
import com.padcmyanmar.smtz.library.activities.BookDetailsActivity
import com.padcmyanmar.smtz.library.data.vos.BookVO
import com.padcmyanmar.smtz.library.data.vos.GenreVO
import com.padcmyanmar.smtz.library.mvp.presenters.YourBookPresenter
import com.padcmyanmar.smtz.library.mvp.presenters.YourBookPresenterImpl
import com.padcmyanmar.smtz.library.mvp.views.YourBookView
import com.padcmyanmar.smtz.library.views.viewpods.SortingAndDisplayViewPod
import kotlinx.android.synthetic.main.bottom_sheet_more_action_home.*
import kotlinx.android.synthetic.main.bottom_sheet_more_action_library.*
import kotlinx.android.synthetic.main.bottom_sheet_sorting.*
import kotlinx.android.synthetic.main.bottom_sheet_view.*
import kotlinx.android.synthetic.main.fragment_your_books.*
import kotlinx.android.synthetic.main.view_pod_sorting_and_display.*

class YourBooksFragment : Fragment(), YourBookView {

    private lateinit var mSortingAndDisplayViewPod: SortingAndDisplayViewPod
    private lateinit var mPresenter: YourBookPresenter

    private var mBooks: List<BookVO> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_your_books, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpPresenter()

        setUpViewPod("SmallGrid")

        mPresenter.onUiReady(this)

        super.onViewCreated(view, savedInstanceState)
    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this).get(YourBookPresenterImpl::class.java)
        mPresenter.initView(this)
    }

    private fun setUpViewPod(viewType: String) {
        mSortingAndDisplayViewPod = vpSortingAndDisplay as SortingAndDisplayViewPod

        mSortingAndDisplayViewPod.setDelegateViewPod(
            detailDelegate = mPresenter,
            libraryViewsDelegate = mPresenter,
            sortBooksDelegate = mPresenter,
            genreDelegate = mPresenter,
            viewType
        )
    }

    override fun onTapView() {

            val dialog = setUpBottomSheet(R.layout.bottom_sheet_view)

            //RadioButton-----
            dialog.radioButtonList.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    dialog.radioButtonLargeGrid.isChecked = false
                    dialog.radioButtonSmallGrid.isChecked = false
                    setUpViewPod("List")
                    dialog.dismiss()
                }
            }
            dialog.radioButtonLargeGrid.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    dialog.radioButtonList.isChecked = false
                    dialog.radioButtonSmallGrid.isChecked = false
                    setUpViewPod("LargeGrid")
                    dialog.dismiss()
                }
            }
            dialog.radioButtonSmallGrid.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    dialog.radioButtonLargeGrid.isChecked = false
                    dialog.radioButtonList.isChecked = false
                    setUpViewPod("SmallGrid")
                    dialog.dismiss()
                }
            }
    }

    override fun onTapSort() {
        val dialog = setUpBottomSheet(R.layout.bottom_sheet_sorting)

        //RadioButton-----
        dialog.radioButtonRecentlyOpened.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                dialog.radioButtonTitle.isChecked = false
                dialog.radioButtonAuthor.isChecked = false
                mSortingAndDisplayViewPod.sortBooks("Recent")
                dialog.dismiss()
            }
        }
        dialog.radioButtonTitle.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                dialog.radioButtonRecentlyOpened.isChecked = false
                dialog.radioButtonAuthor.isChecked = false
                mSortingAndDisplayViewPod.sortBooks("Title")
                dialog.dismiss()
            }
        }
        dialog.radioButtonAuthor.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                dialog.radioButtonRecentlyOpened.isChecked = false
                dialog.radioButtonTitle.isChecked = false
                mSortingAndDisplayViewPod.sortBooks("Author")
                dialog.dismiss()
            }
        }

    }

    override fun showGenreList(genreList: List<GenreVO>) {
        mSortingAndDisplayViewPod.setGenreListData(genreList)
    }

    override fun showRecentBookList(books: List<BookVO>) {
        mBooks = books
        mSortingAndDisplayViewPod.setBookListData(books)
    }

    override fun showRecentBookListByName(books: List<BookVO>) {
        mSortingAndDisplayViewPod.setBookListDataByGenre(books)
    }

    override fun onTapGenre(genres: List<GenreVO>) {
        mSortingAndDisplayViewPod.onTapGenre(genres)
    }

    // Detail Screen
    override fun navigateToBookDetail(book: BookVO) {
        startActivity(BookDetailsActivity.newIntent(requireContext(), book))
    }

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
