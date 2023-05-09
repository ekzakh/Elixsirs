package com.ekzakh.elixsirs.presentation

import com.ekzakh.elixsirs.domain.ElixirsInteractor
import com.github.johnnysc.coremvvm.core.Dispatchers
import com.github.johnnysc.coremvvm.presentation.BaseViewModel

interface ElixirsViewModel : ChangeExpanded, Retry {

    class Base(
        private val interactor: ElixirsInteractor,
        private val communications: ElixirsCommunications,
        dispatchers: Dispatchers,
    ) : BaseViewModel<ElixirsState>(communications, dispatchers), ElixirsViewModel {

        private var elixirs: ElixirsState = ElixirsState.Base(emptyList())

        init {
            init()
        }

        private fun init() {
            communications.map(ElixirsState.Progress(LoadingUi()))
            handle {
                interactor.elixirs {
                    elixirs = it
                    communications.map(it)
                }
            }
        }

        override fun changeExpanded(elixirId: String) {
            elixirs.changeExpanded(elixirId)
            communications.map(elixirs)
        }

        override fun retry() {
            init()
        }
    }

    class Empty : ElixirsViewModel {
        override fun changeExpanded(elixirId: String) = Unit

        override fun retry() = Unit
    }
}
