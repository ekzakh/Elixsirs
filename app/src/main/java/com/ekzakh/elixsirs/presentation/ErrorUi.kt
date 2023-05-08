package com.ekzakh.elixsirs.presentation

import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi
import com.github.johnnysc.coremvvm.presentation.adapter.MyView

class ErrorUi(private val message: String) : ItemUi {
    override fun content(): String = message

    override fun id(): String = message

    override fun show(vararg views: MyView) {
        views[0].show(message)
    }

    override fun type(): Int = ERROR_UI_TYPE

    companion object {
        const val ERROR_UI_TYPE = 3
    }
}
