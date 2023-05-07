package com.ekzakh.elixsirs.presentation

interface ChangeExpanded {

    fun changeExpanded(elixirId: String)

    class Empty : ChangeExpanded {
        override fun changeExpanded(elixirId: String) = Unit
    }
}
