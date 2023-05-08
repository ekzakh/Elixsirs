package com.ekzakh.elixsirs.presentation.adapter

import com.ekzakh.elixsirs.databinding.ErrorItemBinding
import com.github.johnnysc.coremvvm.presentation.adapter.GenericViewHolder
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi

class ErrorViewHolder(private val binding: ErrorItemBinding) :
    GenericViewHolder<ItemUi>(binding.root) {
    override fun bind(item: ItemUi) = item.show(binding.error)
}
