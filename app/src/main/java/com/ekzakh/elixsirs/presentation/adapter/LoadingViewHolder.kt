package com.ekzakh.elixsirs.presentation.adapter

import com.ekzakh.elixsirs.databinding.LoadingItemBinding
import com.github.johnnysc.coremvvm.presentation.adapter.GenericViewHolder
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi

class LoadingViewHolder(binding: LoadingItemBinding) :
    GenericViewHolder<ItemUi>(binding.root) {
    override fun bind(item: ItemUi) = Unit
}
