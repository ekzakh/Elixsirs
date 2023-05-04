package com.ekzakh.elixsirs.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.ekzakh.elixsirs.databinding.ElixirItemBinding
import com.ekzakh.elixsirs.presentation.ElixirUi
import com.github.johnnysc.coremvvm.presentation.adapter.GenericViewHolder
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi
import com.github.johnnysc.coremvvm.presentation.adapter.ViewHolderFactoryChain

class ElixirViewHolderFactoryChain(
    private val viewHolderFactoryChain: ViewHolderFactoryChain<ItemUi>,
) : ViewHolderFactoryChain<ItemUi> {

    override fun viewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder<ItemUi> =
        if (viewType == ElixirUi.ELIXIR_UI_TYPE) {
            ElixirViewHolder(
                ElixirItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        } else {
            viewHolderFactoryChain.viewHolder(parent, viewType)
        }
}
