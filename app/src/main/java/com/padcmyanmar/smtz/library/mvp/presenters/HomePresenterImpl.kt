package com.padcmyanmar.smtz.library.mvp.presenters

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.padcmyanmar.smtz.library.data.models.LibraryModel
import com.padcmyanmar.smtz.library.data.models.LibraryModelImpl
import com.padcmyanmar.smtz.library.data.vos.BookVO
import com.padcmyanmar.smtz.library.mvp.views.HomeView
import kotlinx.android.synthetic.main.fragment_home.*

class HomePresenterImpl : ViewModel(), HomePresenter {

    //View
    var mView: HomeView? = null

    //Model
    private var mLibraryModel : LibraryModel = LibraryModelImpl

    //States

    override fun initView(view: HomeView) {
        mView = view
    }

    override fun onUiReady(owner: LifecycleOwner) {
//        mLibraryModel.getBookList {
//            mView?.showError(it)
//        }?.observe(owner) {
//            it?.let{
//                mView?.showBookLists(it)
//            }
//        }
        mLibraryModel.getRecentBooksFromDB()
            ?.observe(owner){ books ->
                if(books.isEmpty()){
                    mView?.showEmptyView()
                }else{
                    mView?.hideEmptyView()
                    mView?.showRecentBookList(books)
                }
            }
    }

    // Detail Screen
    override fun onTapBook(book: BookVO) {
        mView?.navigateToBookDetail(book)

    }

    override fun onTapMoreAction(viewType: String, book: BookVO, bookListName: String) {
        mView?.onTapMoreAction(viewType, book, bookListName)
    }

}