package com.ekzakh.elixsirs.presentation.adapter

import com.ekzakh.elixsirs.databinding.IngredientItemBinding
import com.github.johnnysc.coremvvm.presentation.adapter.GenericViewHolder
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi

class IngredientViewHolder(private val binding: IngredientItemBinding) :
    GenericViewHolder<ItemUi>(binding.root) {
    override fun bind(item: ItemUi) = item.show(binding.name)
}
