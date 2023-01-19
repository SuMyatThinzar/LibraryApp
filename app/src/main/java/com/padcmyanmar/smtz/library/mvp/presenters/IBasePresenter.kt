package com.padcmyanmar.smtz.library.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.padcmyanmar.smtz.library.data.vos.BookVO

interface IBasePresenter {
    fun onUiReady(owner: LifecycleOwner)
}