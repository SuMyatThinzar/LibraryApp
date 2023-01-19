package com.padcmyanmar.smtz.library.delegate

import com.padcmyanmar.smtz.library.data.vos.ShelfVO

interface ShelfDetailDelegate {
    fun onTapShelf(shelf: ShelfVO)
}