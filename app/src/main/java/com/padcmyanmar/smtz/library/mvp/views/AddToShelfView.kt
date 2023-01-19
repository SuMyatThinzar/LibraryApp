package com.padcmyanmar.smtz.library.mvp.views

import com.padcmyanmar.smtz.library.data.vos.ShelfVO

interface AddToShelfView : BaseView  {
    fun showShelfList(shelfList: List<ShelfVO>)
    fun navigateToYourShelvesScreen()
}