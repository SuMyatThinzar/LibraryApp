package com.padcmyanmar.smtz.library.delegate

import com.padcmyanmar.smtz.library.data.vos.ShelfVO

interface AddToShelfCheckBoxDelegate {
    fun onTapCheckBoxAddShelf(shelfVO: ShelfVO)
    fun onTapCheckBoxRemoveShelf(shelfVO: ShelfVO)
}