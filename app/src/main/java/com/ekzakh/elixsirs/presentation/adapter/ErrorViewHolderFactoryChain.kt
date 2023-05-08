package com.ekzakh.elixsirs.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.ekzakh.elixsirs.databinding.ErrorItemBinding
import com.ekzakh.elixsirs.presentation.ErrorUi
import com.github.johnnysc.coremvvm.presentation.adapter.GenericViewHolder
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi
import com.github.johnnysc.coremvvm.presentation.adapter.ViewHolderFactoryChain

class ErrorViewHolderFactoryChain(
    private val viewHolderFactoryChain: ViewHolderFactoryChain<ItemUi>,
) : ViewHolderFactoryChain<ItemUi> {

    override fun viewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder<ItemUi> =
        if (viewType == ErrorUi.ERROR_UI_TYPE) {
            ErrorViewHolder(
                ErrorItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        } else {
            viewHolderFactoryChain.viewHolder(parent, viewType)
        }
}
