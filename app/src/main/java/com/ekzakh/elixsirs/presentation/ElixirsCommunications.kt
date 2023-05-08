package com.ekzakh.elixsirs.presentation

import com.github.johnnysc.coremvvm.presentation.Communication

interface ElixirsCommunications : Communication.Mutable<ElixirsState> {
    class Base : Communication.UiUpdate<ElixirsState>(), ElixirsCommunications
}
