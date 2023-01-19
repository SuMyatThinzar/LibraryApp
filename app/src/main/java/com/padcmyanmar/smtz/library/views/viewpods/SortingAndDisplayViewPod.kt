package com.padcmyanmar.smtz.library.views.viewpods

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.widget.RelativeLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.padcmyanmar.smtz.library.adapters.GenreChipAdapter
import com.padcmyanmar.smtz.library.adapters.LargeGridViewAdapter
import com.padcmyanmar.smtz.library.adapters.ListViewAdapter
import com.padcmyanmar.smtz.library.adapters.SmallGridViewAdapter
import com.padcmyanmar.smtz.library.data.models.LibraryModel
import com.padcmyanmar.smtz.library.data.vos.BookVO
import com.padcmyanmar.smtz.library.data.vos.GenreVO
import com.padcmyanmar.smtz.library.delegate.BookDetailsDelegate
import com.padcmyanmar.smtz.library.delegate.GenreDelegate
import com.padcmyanmar.smtz.library.delegate.LibraryViewsDelegate
import com.padcmyanmar.smtz.library.delegate.SortBooksDelegate
import kotlinx.android.synthetic.main.view_pod_sorting_and_display.view.*

class SortingAndDisplayViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : RelativeLayout(context, attrs) {

    private lateinit var mGenreChipsAdapter: GenreChipAdapter
    private lateinit var mLargeGridAdapter: LargeGridViewAdapter
    private lateinit var mListAdapter: ListViewAdapter
    private lateinit var mSmallGridAdapter: SmallGridViewAdapter

    private lateinit var mDetailDelegate: BookDetailsDelegate
    private lateinit var mLibraryViewsDelegate: LibraryViewsDelegate
    private lateinit var mSortBooksDelegate: SortBooksDelegate
    private lateinit var mGenreDelegate: GenreDelegate
    private lateinit var mViewType: String

    private var mBooks: List<BookVO> = listOf()
    private var mBooksByGenre: List<BookVO> = listOf()
    private var mGenreList: List<GenreVO> = listOf()

    override fun onFinishInflate() {
//        setUpAdapter()
        setUpListeners()
        super.onFinishInflate()
    }

    fun setDelegateViewPod(
        detailDelegate: BookDetailsDelegate,
        libraryViewsDelegate: LibraryViewsDelegate,
        sortBooksDelegate: SortBooksDelegate,
        genreDelegate: GenreDelegate,
        viewType: String
    ) {
        this.mDetailDelegate = detailDelegate
        this.mLibraryViewsDelegate = libraryViewsDelegate
        this.mSortBooksDelegate = sortBooksDelegate
        this.mGenreDelegate = genreDelegate
        this.mViewType = viewType

        setUpAdapter()
    }

    private fun setUpAdapter() {

        // Genre RecyclerView
        mGenreChipsAdapter = GenreChipAdapter(mGenreDelegate)
        rvGenreChips.adapter = mGenreChipsAdapter
        rvGenreChips.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        setUpBookViewsAdapter(mViewType, mBooks)
        mGenreChipsAdapter.setNewData(mGenreList)
    }

    private fun setUpBookViewsAdapter(viewType: String, books: List<BookVO>) {
        when (viewType) {
            "LargeGrid" -> {
                mLargeGridAdapter = LargeGridViewAdapter(mDetailDelegate)
                rvLibraryBooks.adapter = mLargeGridAdapter
                rvLibraryBooks.layoutManager = GridLayoutManager(context, 2)
                mLargeGridAdapter.setNewData(books)
            }
            "List" -> {
                mListAdapter = ListViewAdapter(mDetailDelegate)
                rvLibraryBooks.adapter = mListAdapter
                rvLibraryBooks.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                mListAdapter.setNewData(books)
            }
            else -> {
                mSmallGridAdapter = SmallGridViewAdapter(mDetailDelegate)
                rvLibraryBooks.adapter = mSmallGridAdapter
                rvLibraryBooks.layoutManager = GridLayoutManager(context, 3)
                mSmallGridAdapter.setNewData(books)
            }
        }
    }

    private fun setUpListeners() {

        btnView.setOnClickListener {
            mLibraryViewsDelegate.onTapView()
        }
        btnUnselectAll.setOnClickListener {
            mGenreList.forEach {
                it.isSelected = false
                mGenreChipsAdapter.setNewData(mGenreList)

                setUpBookViewsAdapter(mViewType, mBooks)
            }
        }
        btnSort.setOnClickListener {
            mSortBooksDelegate.onTapSort()
        }
    }

    // bind Books from Fragment/Presenter onUiReady
    fun setBookListData(books: List<BookVO>) {
        mBooks = books
        setUpBookViewsAdapter(mViewType, mBooks)                      // *** For Initial View
    }

    fun setBookListDataByGenre(booksByGenre: List<BookVO>) {
        mBooksByGenre = booksByGenre
        setUpBookViewsAdapter(mViewType, booksByGenre)               // *** For Initial View
    }

    // bind Genres from Fragment/Presenter onUiReady
    fun setGenreListData(genreList: List<GenreVO>) {
        mGenreList = genreList
        setUpAdapter()                                               // *** For Initial View
    }

    // GenreDelegate from Fragment
    fun onTapGenre(genres: List<GenreVO>) {
            mGenreChipsAdapter.setNewData(genres)
//        }

    }

    fun sortBooks(type: String) {
        var year = 0
        var month = 0
        var day = 0
        when (type) {
            "Recent" -> for (book in mBooks) {
                year = book.updatedDate?.substring(0, 4)!!.toInt()
                month = book.updatedDate.substring(5, 7).toInt()
                day = book.updatedDate.substring(8, 10).toInt()

                Log.d("Date", "$year + $month + $day")
            }
            "Title" -> {
//                for(book in mBooks){
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                    var sort = mBooks.stream().sorted().collect(Collectors.toList())
//                    Log.d("Sort", sort.toString())

                setUpBookViewsAdapter(
                    mViewType,
                    mBooks.sortedBy { it.title.lowercase() }.reversed()
                )
            }

            "Author" -> {

                setUpBookViewsAdapter(
                    mViewType,
                    mBooks.sortedBy { it.author?.lowercase() }.reversed()
                )
            }
        }
    }
}