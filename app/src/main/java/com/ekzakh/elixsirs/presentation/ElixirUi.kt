package com.ekzakh.elixsirs.presentation

import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi
import com.github.johnnysc.coremvvm.presentation.adapter.MyView

data class ElixirUi(
    private val id: String,
    private val name: String,
    private val effect: String,
    private val isExpanded: Boolean,
) : ItemUi {
    override fun type(): Int = ELIXIR_UI_TYPE

    override fun show(vararg views: MyView) {
        views[0].show(name)
        views[1].show(effect)
        // todo views[2].handleClick()
    }

    override fun id(): String = id

    override fun content(): String = name + effect

    companion object {
        private const val ELIXIR_UI_TYPE = 1
    }
}
