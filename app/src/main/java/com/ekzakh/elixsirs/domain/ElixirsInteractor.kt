package com.ekzakh.elixsirs.domain

import com.ekzakh.elixsirs.presentation.ElixirsState
import com.github.johnnysc.coremvvm.core.Dispatchers
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi

interface ElixirsInteractor {
    suspend fun elixirs(onResult: (ElixirsState) -> Unit)

    class Base(
        private val mapper: ElixirDomain.Mapper<List<ItemUi>>,
        private val repository: ElixirsRepository,
        private val dispatchers: Dispatchers,
        private val errorHandler: DomainExceptionHandler.Mapper<ItemUi>
    ) : ElixirsInteractor {

        override suspend fun elixirs(onResult: (ElixirsState) -> Unit) {
            try {
                val result = repository.elixirs().flatMap { it.map(mapper) }
                dispatchers.changeToUI { onResult.invoke(ElixirsState.Success(result)) }
            } catch (error: Exception) {
                dispatchers.changeToUI {
                    onResult.invoke(ElixirsState.Error(errorHandler.map(error)))
                }
            }
        }
    }
}
