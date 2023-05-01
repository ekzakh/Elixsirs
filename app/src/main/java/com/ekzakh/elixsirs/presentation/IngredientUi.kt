package com.ekzakh.elixsirs.presentation

import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi
import com.github.johnnysc.coremvvm.presentation.adapter.MyView

data class IngredientUi(
    private val id: String,
    private val name: String,
) : ItemUi {
    override fun content(): String = name

    override fun id(): String = id

    override fun show(vararg views: MyView) {
        views[0].show(name)
    }

    override fun type(): Int = INGREDIENT_TYPE

    companion object {
        private const val INGREDIENT_TYPE = 2
    }
}
