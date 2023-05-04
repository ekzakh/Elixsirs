package com.ekzakh.elixsirs.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.ekzakh.elixsirs.databinding.IngredientItemBinding
import com.ekzakh.elixsirs.presentation.IngredientUi
import com.github.johnnysc.coremvvm.presentation.adapter.GenericViewHolder
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi
import com.github.johnnysc.coremvvm.presentation.adapter.ViewHolderFactoryChain

class IngredientViewHolderFactoryChain(
    private val viewHolderFactoryChain: ViewHolderFactoryChain<ItemUi>,
) : ViewHolderFactoryChain<ItemUi> {

    override fun viewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder<ItemUi> =
        if (viewType == IngredientUi.INGREDIENT_UI_TYPE) {
            IngredientViewHolder(
                IngredientItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        } else {
            viewHolderFactoryChain.viewHolder(parent, viewType)
        }
}
