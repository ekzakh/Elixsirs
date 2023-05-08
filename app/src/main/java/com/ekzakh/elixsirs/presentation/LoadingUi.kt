package com.ekzakh.elixsirs.presentation

import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi
import com.github.johnnysc.coremvvm.presentation.adapter.MyView

class LoadingUi : ItemUi {
    override fun content(): String = ""

    override fun id(): String = type().toString()

    override fun show(vararg views: MyView) = Unit

    override fun type(): Int = LOADING_UI_TYPE

    companion object {
        const val LOADING_UI_TYPE = 4
    }
}
