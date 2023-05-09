package com.ekzakh.elixsirs.presentation.adapter

import com.ekzakh.elixsirs.databinding.ElixirItemBinding
import com.github.johnnysc.coremvvm.presentation.adapter.GenericViewHolder
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi

class ElixirViewHolder(private val binding: ElixirItemBinding) :
    GenericViewHolder<ItemUi>(binding.root) {
    override fun bind(item: ItemUi) = with(binding) {
        item.show(nameTextView, effectTextView, dropIcon)
    }
}
