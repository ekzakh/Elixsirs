package com.ekzakh.elixsirs.data

import com.github.johnnysc.coremvvm.data.CloudDataSource
import com.github.johnnysc.coremvvm.data.HandleError

interface ElixirsCloudDataSource {

    suspend fun elixirs(): ElixirsCloud

    class Base(
        private val elixirService: ElixirService,
        handleError: HandleError,
    ) : CloudDataSource.Abstract(handleError), ElixirsCloudDataSource {
        override suspend fun elixirs(): ElixirsCloud = handle {
            val result = elixirService.elixirs()
            ElixirsCloud.Base(result)
        }
    }
}
