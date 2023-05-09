package com.ekzakh.elixsirs.data

import com.github.johnnysc.coremvvm.data.CloudDataSource
import com.github.johnnysc.coremvvm.data.HandleError

interface ElixirsCloudDataSource {

    suspend fun elixirs(): List<ElixirCloud>

    class Base(
        private val elixirService: ElixirService,
        handleError: HandleError,
    ) : CloudDataSource.Abstract(handleError), ElixirsCloudDataSource {
        override suspend fun elixirs(): List<ElixirCloud> = handle {
            elixirService.elixirs().filter { it.hasIngredients() }
        }
    }
}
