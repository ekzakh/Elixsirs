package com.ekzakh.elixsirs.presentation

import com.ekzakh.elixsirs.domain.ElixirsInteractor
import com.github.johnnysc.coremvvm.core.Dispatchers
import com.github.johnnysc.coremvvm.presentation.BaseViewModel

interface ElixirsViewModel : ChangeExpanded {

    class Base(
        private val interactor: ElixirsInteractor,
        private val communications: ElixirsCommunications,
        dispatchers: Dispatchers,
    ) : BaseViewModel<ElixirsState>(communications, dispatchers), ElixirsViewModel, ChangeExpanded {

        private var elixirs: ElixirsState = ElixirsState.Progress(LoadingUi())

        init {
            communications.map(elixirs)
            handle {
                interactor.elixirs {
                    elixirs = it
                    communications.map(it)
                }
            }
        }

        override fun changeExpanded(elixirId: String) {
            (elixirs as ElixirsState.Base).changeExpanded(elixirId)
            communications.map(elixirs)
        }
    }
}
