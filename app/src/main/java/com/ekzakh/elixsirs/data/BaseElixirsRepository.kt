package com.ekzakh.elixsirs.data

import com.ekzakh.elixsirs.domain.ElixirDomain
import com.ekzakh.elixsirs.domain.ElixirsRepository

class BaseElixirsRepository(
    private val elixirsCloudDataSource: ElixirsCloudDataSource,
    private val mapper: ElixirCloud.Mapper<ElixirDomain>,
) : ElixirsRepository {
    override suspend fun elixirs(): List<ElixirDomain> {
        return elixirsCloudDataSource.elixirs().map { it.map(mapper) }
    }
}
