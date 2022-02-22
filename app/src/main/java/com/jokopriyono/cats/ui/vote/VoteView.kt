package com.jokopriyono.cats.ui.vote

import com.jokopriyono.cats.model.SearchResponseItem

interface VoteView {

    fun refreshCat()

    fun showCat(cat: SearchResponseItem)

    fun showError(message: String)

}