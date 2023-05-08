package com.ekzakh.elixsirs.presentation.adapter

import com.github.johnnysc.coremvvm.presentation.adapter.GenericAdapter
import com.github.johnnysc.coremvvm.presentation.adapter.ViewHolderFactoryChain

interface ElixirsAdapter {

    class Elixirs : GenericAdapter.Base(
        ElixirViewHolderFactoryChain(
            IngredientViewHolderFactoryChain(
                ErrorViewHolderFactoryChain(
                    ViewHolderFactoryChain.Exception()
                )
            )
        )
    )
}
