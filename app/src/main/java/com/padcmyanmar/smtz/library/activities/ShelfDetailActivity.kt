package com.padcmyanmar.smtz.library.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.padcmyanmar.smtz.library.R
import com.padcmyanmar.smtz.library.data.vos.BookVO
import com.padcmyanmar.smtz.library.data.vos.GenreVO
import com.padcmyanmar.smtz.library.data.vos.ShelfVO
import com.padcmyanmar.smtz.library.mvp.presenters.ShelfDetailPresenter
import com.padcmyanmar.smtz.library.mvp.presenters.ShelfDetailPresenterImpl
import com.padcmyanmar.smtz.library.mvp.views.ShelfDetailView
import com.padcmyanmar.smtz.library.views.viewpods.SortingAndDisplayViewPod
import kotlinx.android.synthetic.main.activity_create_shelf.*
import kotlinx.android.synthetic.main.activity_shelf_detail.*
import kotlinx.android.synthetic.main.bottom_sheet_more_action_home.*
import kotlinx.android.synthetic.main.bottom_sheet_more_action_shelf.*
import kotlinx.android.synthetic.main.bottom_sheet_sorting.*
import kotlinx.android.synthetic.main.bottom_sheet_view.*
import kotlinx.android.synthetic.main.view_pod_sorting_and_display.*

class ShelfDetailActivity : AppCompatActivity(), ShelfDetailView {

    private lateinit var mPresenter: ShelfDetailPresenter

    private lateinit var mSortingAndDisplayViewPod: SortingAndDisplayViewPod

    private var mShelfId: Long = 0

    companion object{

        private const val EXTRA_SHELF_ID = "EXTRA SHELF ID"

        fun newIntent(context: Context, shelfId: Long): Intent {
            val intent = Intent(context, ShelfDetailActivity::class.java)

            intent.putExtra(EXTRA_SHELF_ID, shelfId)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shelf_detail)

        setUpPresenter()

        mShelfId = intent.getLongExtra(EXTRA_SHELF_ID, 0)

        setUpViewPod("SmallGrid")
        setUpListeners()

        etShelfReName.visibility = View.INVISIBLE

        mPresenter.onUiReadyShelfDetail(this, mShelfId)
    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this)[ShelfDetailPresenterImpl::class.java]
        mPresenter.initView(this)
    }

    private fun setUpListeners(){
        btnBack.setOnClickListener {
            mPresenter.onTapBack()
        }
        btnMoreActionShelfDetail.setOnClickListener {
            mPresenter.onTapShelfMore()
        }
        etShelfReName.setOnEditorActionListener{ text, actionId, _ ->
            return@setOnEditorActionListener when(actionId){
                EditorInfo.IME_ACTION_DONE -> {
                    mPresenter.onShelfRename(text.text.toString())
                    tvShelfNameShelfDetail.text = text.text.toString()

                    etShelfReName.visibility = View.INVISIBLE
                    tvShelfNameShelfDetail.visibility = View.VISIBLE
                    true
                }
                else -> false
            }
        }
    }

    private fun setUpViewPod(viewType: String) {
        mSortingAndDisplayViewPod = vpSortingAndDisplayShelfDetail as SortingAndDisplayViewPod

        mSortingAndDisplayViewPod.setDelegateViewPod(
            detailDelegate = mPresenter,
            libraryViewsDelegate = mPresenter,
            sortBooksDelegate = mPresenter,
            genreDelegate = mPresenter,
            viewType
        )
    }

    override fun showShelfDetail(shelf: ShelfVO) {
        bindData(shelf)
    }

    override fun showGenreList(genres: MutableList<GenreVO>) {

        mSortingAndDisplayViewPod.setGenreListData(genres)
    }

    override fun navigateToBookDetail(book: BookVO) {
        startActivity(BookDetailsActivity.newIntent(this, book))
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

    override fun onTapGenre(genres: List<GenreVO>) {
        mSortingAndDisplayViewPod.onTapGenre(genres)
    }

    override fun showRecentBookListByName(books: List<BookVO>) {
        mSortingAndDisplayViewPod.setBookListDataByGenre(books)
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

    override fun navigateBack() {
        super.onBackPressed()
    }

    override fun showShelfUpdateBottomSheet(shelf: ShelfVO) {
        val dialog = setUpBottomSheet(R.layout.bottom_sheet_more_action_shelf)

        dialog.tvShelfNameBottomSheetLibrary.text = shelf.shelfName

        dialog.llRenameShelf.setOnClickListener {
            tvShelfNameShelfDetail.visibility = View.INVISIBLE
            etShelfReName.visibility = View.VISIBLE
            etShelfReName.setText(shelf.shelfName)
            dialog.dismiss()
        }

        dialog.llDeleteShelf.setOnClickListener {
            mPresenter.onDeleteShelf()
        }
    }

    private fun setUpBottomSheet(view: Int): BottomSheetDialog {
        val dialog = BottomSheetDialog(this)
        dialog.setContentView(view)
        dialog.show()
        return dialog
    }

    private fun bindData(shelf: ShelfVO){
        tvShelfNameShelfDetail.text = shelf.shelfName
        tvBookCountShelfDetail.text = "${shelf.books.count()} books"
        mSortingAndDisplayViewPod.setBookListData(shelf.books)
    }

    override fun showError(errorString: String) {
        Snackbar.make(window.decorView, errorString, Snackbar.LENGTH_SHORT).show()
    }
}