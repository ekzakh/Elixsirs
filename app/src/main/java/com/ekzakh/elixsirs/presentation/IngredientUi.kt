package com.ekzakh.elixsirs.presentation

import com.github.johnnysc.coremvvm.presentation.adapter.MyView

data class IngredientUi(
    private val id: String,
    private val name: String,
) : ElixirsUi {
    override fun content(): String = name

    override fun id(): String = id

    override fun show(vararg views: MyView) {
        views[0].show(name)
    }

    override fun type(): Int = INGREDIENT_UI_TYPE

    override fun <T> map(mapper: ElixirsUi.Mapper<T>): T =
        mapper.map(id, name, "", false, ChangeExpanded.Empty())

    companion object {
        const val INGREDIENT_UI_TYPE = 2
    }
}
