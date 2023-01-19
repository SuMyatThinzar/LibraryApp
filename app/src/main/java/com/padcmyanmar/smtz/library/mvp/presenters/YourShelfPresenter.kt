package com.padcmyanmar.smtz.library.mvp.presenters

import com.padcmyanmar.smtz.library.data.vos.ShelfVO
import com.padcmyanmar.smtz.library.delegate.ShelfDetailDelegate
import com.padcmyanmar.smtz.library.mvp.views.HomeView
import com.padcmyanmar.smtz.library.mvp.views.YourShelfView
import com.padcmyanmar.smtz.library.views.viewpods.EmptyShelfViewPod

interface YourShelfPresenter : IBasePresenter, EmptyShelfViewPod.Delegate, ShelfDetailDelegate {
    fun initView(view: YourShelfView)
}