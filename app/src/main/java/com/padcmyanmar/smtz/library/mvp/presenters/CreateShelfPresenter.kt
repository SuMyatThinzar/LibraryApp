package com.padcmyanmar.smtz.library.mvp.presenters

import com.padcmyanmar.smtz.library.mvp.views.CreateShelfView

interface CreateShelfPresenter : IBasePresenter {
    fun initView(view: CreateShelfView)
    fun onTapComplete(shelfName: String)
    fun onTapBack()
}