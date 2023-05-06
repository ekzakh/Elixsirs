package com.ekzakh.elixsirs.domain

import com.ekzakh.elixsirs.presentation.ElixirsUi
import com.github.johnnysc.coremvvm.core.Dispatchers
import com.github.johnnysc.coremvvm.data.HandleError
import com.github.johnnysc.coremvvm.domain.Interactor

interface ElixirsInteractor {
    suspend fun elixirs(
        atFinish: () -> Unit,
        successful: (ElixirsUi) -> Unit
    )

    class Base(
        private val mapper: ElixirsDomain.Mapper<ElixirsUi>,
        private val repository: ElixirsRepository,
        dispatchers: Dispatchers,
        handleError: HandleError
    ) : Interactor.Abstract(dispatchers, handleError), ElixirsInteractor {

        override suspend fun elixirs(
            atFinish: () -> Unit,
            successful: (ElixirsUi) -> Unit,
        ) = handle(successful, atFinish) {
            val data = repository.elixirs()
            return@handle data.map(mapper)
        }
    }
}
