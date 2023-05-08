package com.ekzakh.elixsirs.domain

import com.ekzakh.elixsirs.presentation.ElixirsUi
import com.github.johnnysc.coremvvm.core.Dispatchers

interface ElixirsInteractor {
    suspend fun elixirs(
        atFinish: () -> Unit,
        successful: (ElixirsUi) -> Unit,
    )

    class Base(
        private val mapper: ElixirsDomain.Mapper<ElixirsUi>,
        private val repository: ElixirsRepository,
        private val dispatchers: Dispatchers,
        private val errorHandler: DomainExceptionHandler.Mapper<ElixirsUi>
    ) : ElixirsInteractor {

        override suspend fun elixirs(
            atFinish: () -> Unit,
            successful: (ElixirsUi) -> Unit
        ) {
            try {
                val result = repository.elixirs().map(mapper)
                dispatchers.changeToUI { successful.invoke(result) }
            } catch (error: Exception) {
                dispatchers.changeToUI { successful.invoke(errorHandler.map(error)) }
            } finally {
                dispatchers.changeToUI { atFinish.invoke() }
            }
        }
    }
}
