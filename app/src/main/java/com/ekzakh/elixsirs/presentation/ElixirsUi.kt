package com.ekzakh.elixsirs.presentation

import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi

interface ElixirsUi : ItemUi {

    fun <T> map(mapper: Mapper<T>): T

    interface Mapper<T> {
        fun map(
            id: String,
            name: String,
            effect: String,
            isExpanded: Boolean,
            changeExpanded: ChangeExpanded
        ): T

        class InverseExpanded : Mapper<ElixirUi> {
            override fun map(
                id: String,
                name: String,
                effect: String,
                isExpanded: Boolean,
                changeExpanded: ChangeExpanded
            ): ElixirUi = ElixirUi.Base(id, name, effect, !isExpanded, changeExpanded)
        }

        class IsExpanded : Mapper<Boolean> {
            override fun map(
                id: String,
                name: String,
                effect: String,
                isExpanded: Boolean,
                changeExpanded: ChangeExpanded
            ): Boolean = isExpanded
        }
    }
}
