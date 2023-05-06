package com.ekzakh.elixsirs.presentation

import com.github.johnnysc.coremvvm.presentation.Communication

interface ElixirsCommunications : Communication.Mutable<ElixirsUi> {
    class Base : Communication.UiUpdate<ElixirsUi>(), ElixirsCommunications
}
