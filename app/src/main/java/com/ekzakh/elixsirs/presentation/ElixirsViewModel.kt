package com.ekzakh.elixsirs.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.ekzakh.elixsirs.domain.ElixirsInteractor
import com.github.johnnysc.coremvvm.core.Dispatchers
import com.github.johnnysc.coremvvm.presentation.BaseViewModel
import com.github.johnnysc.coremvvm.presentation.GlobalErrorCommunication
import com.github.johnnysc.coremvvm.presentation.ProgressCommunication
import com.github.johnnysc.coremvvm.presentation.Visibility

interface ElixirsViewModel : ChangeExpanded {

    fun observeProgress(owner: LifecycleOwner, observer: Observer<Visibility>)
    fun observeError(owner: LifecycleOwner, observer: Observer<String>)

    class Base(
        private val interactor: ElixirsInteractor,
        private val communications: ElixirsCommunications,
        private val progressCommunication: ProgressCommunication.Mutable,
        private val errorCommunication: GlobalErrorCommunication.Observe,
        dispatchers: Dispatchers,
    ) : BaseViewModel<ElixirsUi>(communications, dispatchers), ElixirsViewModel, ChangeExpanded {

        private var elixirs: ElixirsUi = ElixirsUi.Base(emptyList())

        private val atFinish = {
            progressCommunication.map(Visibility.Gone())
        }

        init {
            progressCommunication.map(Visibility.Visible())
            handle {
                interactor.elixirs(atFinish) {
                    elixirs = it
                    communications.map(it)
                }
            }
        }

        override fun observeProgress(owner: LifecycleOwner, observer: Observer<Visibility>) {
            progressCommunication.observe(owner, observer)
        }

        override fun observeError(owner: LifecycleOwner, observer: Observer<String>) {
            errorCommunication.observe(owner, observer)
        }

        override fun changeExpanded(elixirId: String) {
            elixirs.changeExpanded(elixirId)
            communications.map(elixirs)
        }
    }
}
