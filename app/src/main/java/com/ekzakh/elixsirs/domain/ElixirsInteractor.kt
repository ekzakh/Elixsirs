package com.ekzakh.elixsirs.domain

import com.ekzakh.elixsirs.presentation.ElixirsState
import com.github.johnnysc.coremvvm.core.Dispatchers

interface ElixirsInteractor {
    suspend fun elixirs(onResult: (ElixirsState) -> Unit)

    class Base(
        private val mapper: ElixirsDomain.Mapper<ElixirsState>,
        private val repository: ElixirsRepository,
        private val dispatchers: Dispatchers,
        private val errorHandler: DomainExceptionHandler.Mapper<ElixirsState>
    ) : ElixirsInteractor {

        override suspend fun elixirs(onResult: (ElixirsState) -> Unit) {
            try {
                val result = repository.elixirs().map(mapper)
                dispatchers.changeToUI { onResult.invoke(result) }
            } catch (error: Exception) {
                dispatchers.changeToUI { onResult.invoke(errorHandler.map(error)) }
            }
        }
    }
}
