package com.padcmyanmar.smtz.library.mvp.views

import com.padcmyanmar.smtz.library.data.vos.ShelfVO

interface YourShelfView: BaseView  {
    fun navigateToCreateShelfScreen()
    fun showEmptyView()
    fun hideEmptyView()
    fun showShelfList(shelfList: List<ShelfVO>)
    fun navigateToShelfDetailScreen(shelfId: Long)
    fun hideShelfList()
}