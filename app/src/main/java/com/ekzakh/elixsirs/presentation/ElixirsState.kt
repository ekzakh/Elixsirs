package com.ekzakh.elixsirs.presentation

import com.github.johnnysc.coremvvm.core.Mapper
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi

interface ElixirsState : Mapper.Unit<Mapper.Unit<List<ItemUi>>>, ChangeExpanded {

    data class Success(private var list: List<ElixirsUi>) : ElixirsState {

        private var uiList = list.filter { it.type() == ElixirUi.ELIXIR_UI_TYPE }.toMutableList()

        override fun map(data: Mapper.Unit<List<ItemUi>>) {
            return data.map(uiList)
        }

        override fun changeExpanded(elixirId: String) {
            val mapperExpanded = ElixirsUi.Mapper.InverseExpanded()
            val finalList = mutableListOf<ElixirsUi>()
            var scipNext = 0

            uiList.forEach { elixirUi ->
                if (elixirUi.id() == elixirId) {
                    val wasExpanded = elixirUi.map(ElixirsUi.Mapper.IsExpanded())
                    var index = list.indexOf(elixirUi) + 1
                    val ingredients = mutableListOf<ElixirsUi>()
                    val changedItem = elixirUi.map(mapperExpanded)
                    list = list.map { item ->
                        if (item.id() == elixirId) changedItem else item
                    }
                    finalList.add(changedItem)
                    while (index < list.size && list[index] is IngredientUi) {
                        ingredients.add(list[index++])
                    }
                    if (wasExpanded) {
                        scipNext = ingredients.size
                    } else {
                        finalList.addAll(ingredients)
                    }
                } else if (scipNext > 0) {
                    scipNext--
                } else {
                    finalList.add(elixirUi)
                }
            }
            uiList.clear()
            uiList.addAll(finalList)
        }
    }

    data class Error(private val error: ItemUi) : ElixirsState {
        override fun map(data: Mapper.Unit<List<ItemUi>>) = data.map(listOf(error))
        override fun changeExpanded(elixirId: String) = Unit
    }

    data class Progress(private val progress: ItemUi) : ElixirsState {
        override fun map(data: Mapper.Unit<List<ItemUi>>) = data.map(listOf(progress))
        override fun changeExpanded(elixirId: String) = Unit
    }
}
