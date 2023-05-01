package com.ekzakh.elixsirs.domain

import com.ekzakh.elixsirs.presentation.ElixirUi
import com.ekzakh.elixsirs.presentation.ElixirsUi
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
        ) : Mapper<ElixirsUi> {
            override fun map(list: List<ElixirDomain>): ElixirsUi {
                val finalList = mutableListOf<ItemUi>()
                list.forEach { elixirDomain ->
                    finalList.add(elixirDomain.map(mapperElixir))
                    finalList.addAll(elixirDomain.map(mapperIngredients))
                }
                return ElixirsUi.Base(finalList)
            }
        }
    }
}
