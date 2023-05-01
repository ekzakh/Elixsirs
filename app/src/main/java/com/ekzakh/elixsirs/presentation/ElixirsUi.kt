package com.ekzakh.elixsirs.presentation

import com.github.johnnysc.coremvvm.core.Mapper
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi

interface ElixirsUi : Mapper.Unit<Mapper.Unit<List<ItemUi>>> {

    data class Base(private val list: List<ItemUi>) : ElixirsUi {
        override fun map(data: Mapper.Unit<List<ItemUi>>) = data.map(list)
    }
}
