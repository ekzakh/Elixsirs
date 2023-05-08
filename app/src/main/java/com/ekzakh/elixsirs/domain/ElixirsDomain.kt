package com.ekzakh.elixsirs.domain

import com.ekzakh.elixsirs.presentation.ElixirUi
import com.ekzakh.elixsirs.presentation.ElixirsState
import com.ekzakh.elixsirs.presentation.IngredientUi
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi

interface ElixirsDomain {
    fun <T> map(mapper: Mapper<T>): T

    class Base(
        private val list: List<ElixirDomain>,
    ) : ElixirsDomain {
        override fun <T> map(mapper: Mapper<T>): T = mapper.map(list)
    }

    interface Mapper<T> {
        fun map(list: List<ElixirDomain>): T

        class Base(
            private val mapperElixir: ElixirDomain.Mapper<ElixirUi>,
            private val mapperIngredients: ElixirDomain.Mapper<List<IngredientUi>>,
        ) : Mapper<ElixirsState> {
            override fun map(list: List<ElixirDomain>): ElixirsState {
                val finalList = mutableListOf<ItemUi>()
                list.forEach { elixirDomain ->
                    finalList.add(elixirDomain.map(mapperElixir))
                    finalList.addAll(elixirDomain.map(mapperIngredients))
                }
                return ElixirsState.Base(finalList)
            }
        }
    }
}
