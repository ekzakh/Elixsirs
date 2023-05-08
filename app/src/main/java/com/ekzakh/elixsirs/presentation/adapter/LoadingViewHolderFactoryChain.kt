package com.ekzakh.elixsirs.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.ekzakh.elixsirs.databinding.LoadingItemBinding
import com.ekzakh.elixsirs.presentation.LoadingUi
import com.github.johnnysc.coremvvm.presentation.adapter.GenericViewHolder
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi
import com.github.johnnysc.coremvvm.presentation.adapter.ViewHolderFactoryChain

class LoadingViewHolderFactoryChain(
    private val viewHolderFactoryChain: ViewHolderFactoryChain<ItemUi>,
) : ViewHolderFactoryChain<ItemUi> {

    override fun viewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder<ItemUi> =
        if (viewType == LoadingUi.LOADING_UI_TYPE) {
            LoadingViewHolder(
                LoadingItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        } else {
            viewHolderFactoryChain.viewHolder(parent, viewType)
        }
}
