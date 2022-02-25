package com.jokopriyono.cats.ui.vote

import com.jokopriyono.cats.model.SearchResponseItem
import com.jokopriyono.cats.ui.BaseView

interface VoteView: BaseView {

    fun refreshCat()

    fun showCat(cat: SearchResponseItem)

    fun showError(message: String)

}