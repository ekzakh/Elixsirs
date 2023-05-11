package com.ekzakh.elixsirs.presentation

import com.ekzakh.elixsirs.R
import com.github.johnnysc.coremvvm.presentation.adapter.MyView

interface ElixirUi : ElixirsUi {
    data class Base(
        private val id: String,
        private val name: String,
        private val effect: String,
        private val isExpanded: Boolean,
        private val changeExpanded: ChangeExpanded
    ) : ElixirUi {

        override fun type(): Int = ELIXIR_UI_TYPE

        override fun show(vararg views: MyView) {
            views[0].show(name)
            views[1].show(effect)
            views[2].showImageResource(if (isExpanded) R.drawable.drop_up else R.drawable.drop_down)
            views[2].handleClick {
                changeExpanded.changeExpanded(id)
            }
        }

        override fun id(): String = id

        override fun <T> map(mapper: ElixirsUi.Mapper<T>): T =
            mapper.map(id, name, effect, isExpanded, changeExpanded)

        override fun content(): String = name + effect + isExpanded
    }

    companion object {
        const val ELIXIR_UI_TYPE = 1
    }
}
