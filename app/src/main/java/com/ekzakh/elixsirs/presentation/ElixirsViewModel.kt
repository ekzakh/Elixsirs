package com.ekzakh.elixsirs.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.ekzakh.elixsirs.domain.ElixirsInteractor
import com.github.johnnysc.coremvvm.core.Dispatchers
import com.github.johnnysc.coremvvm.presentation.BaseViewModel
import com.github.johnnysc.coremvvm.presentation.ProgressCommunication
import com.github.johnnysc.coremvvm.presentation.Visibility

interface ElixirsViewModel {

    fun observeProgress(owner: LifecycleOwner, observer: Observer<Visibility>)

    class Base(
        private val interactor: ElixirsInteractor,
        private val communications: ElixirsCommunications,
        private val progressCommunication: ProgressCommunication.Mutable,
        dispatchers: Dispatchers,
    ) : BaseViewModel<ElixirsUi>(communications, dispatchers), ElixirsViewModel {

        private lateinit var elixirs: ElixirsUi

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
    }
}
