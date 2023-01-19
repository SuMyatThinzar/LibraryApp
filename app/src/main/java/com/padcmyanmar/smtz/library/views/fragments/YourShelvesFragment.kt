package com.padcmyanmar.smtz.library.views.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.padcmyanmar.smtz.library.R
import com.padcmyanmar.smtz.library.activities.AddToShelfActivity
import com.padcmyanmar.smtz.library.activities.CreateShelfActivity
import com.padcmyanmar.smtz.library.activities.ShelfDetailActivity
import com.padcmyanmar.smtz.library.adapters.ShelfAdapter
import com.padcmyanmar.smtz.library.data.vos.ShelfVO
import com.padcmyanmar.smtz.library.mvp.presenters.YourShelfPresenter
import com.padcmyanmar.smtz.library.mvp.presenters.YourShelfPresenterImpl
import com.padcmyanmar.smtz.library.mvp.views.YourShelfView
import com.padcmyanmar.smtz.library.views.viewpods.EmptyShelfViewPod
import kotlinx.android.synthetic.main.fragment_your_shelves.*

class YourShelvesFragment : Fragment(), YourShelfView {

    private lateinit var mEmptyShelfViewPod: EmptyShelfViewPod
    private lateinit var mPresenter: YourShelfPresenter

    private lateinit var shelfAdapter : ShelfAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_your_shelves, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpPresenter()

        setUpViewPod()
        setUpListeners()

        mPresenter.onUiReady(this)

        super.onViewCreated(view, savedInstanceState)
    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this).get(YourShelfPresenterImpl::class.java)
        mPresenter.initView(this)
    }

    private fun setUpListeners() {
        btnCreateNew.setOnClickListener {
            mPresenter.onTapCreateNewShelf()
        }
    }

    private fun setUpViewPod(){

        mEmptyShelfViewPod = vpEmpty as EmptyShelfViewPod

        shelfAdapter = ShelfAdapter(mPresenter)
        rvShelfList.adapter = shelfAdapter
        rvShelfList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
    }

    override fun navigateToCreateShelfScreen() {
        startActivity(Intent(requireContext(), CreateShelfActivity::class.java))
    }

    override fun navigateToShelfDetailScreen(shelfId: Long) {
        startActivity(ShelfDetailActivity.newIntent(requireContext(), shelfId))
    }

    override fun hideShelfList() {
        shelfAdapter.setNewData(listOf())
    }

    override fun showEmptyView() {
        vpEmpty.visibility = View.VISIBLE
    }

    override fun hideEmptyView() {
        vpEmpty.visibility = View.GONE
    }

    override fun showShelfList(shelfList: List<ShelfVO>) {
        shelfAdapter.setNewData(shelfList)
    }
    override fun showError(errorString: String) {
        Toast.makeText(requireContext(), errorString, Toast.LENGTH_SHORT).show()
    }

}