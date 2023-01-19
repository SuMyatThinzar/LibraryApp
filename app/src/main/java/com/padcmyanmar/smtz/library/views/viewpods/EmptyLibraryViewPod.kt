package com.padcmyanmar.smtz.library.views.viewpods

import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.widget.RelativeLayout
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat.startActivity
import com.padcmyanmar.smtz.library.views.fragments.LibraryFragment
import kotlinx.android.synthetic.main.view_pod_empty_library.view.*

class EmptyLibraryViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : RelativeLayout(context, attrs) {

    override fun onFinishInflate() {
        setUpListeners()
        super.onFinishInflate()
    }

    private fun setUpListeners(){
//        btnGoToLibrary.setOnClickListener{
//            val intent = Intent(context, LibraryFragment::class.java)
//            startActivity(context, intent)
//        }
    }
}