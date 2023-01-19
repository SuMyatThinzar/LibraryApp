package com.padcmyanmar.smtz.library.views.viewpods

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.view_pod_empty_shelf.view.*

class EmptyShelfViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {

    private var mDelegate: Delegate? = null

    override fun onFinishInflate() {
        super.onFinishInflate()
//        setUpListeners()
    }

    fun setDelegate(mCreateShelfDelegate: Delegate) {
        mDelegate = mCreateShelfDelegate
    }

//    private fun setUpListeners() {
//        btnCreateNew.setOnClickListener {
//            mDelegate?.onTapCreateNewShelf()
//        }
//    }

    interface Delegate {
        fun onTapCreateNewShelf()
    }
}
