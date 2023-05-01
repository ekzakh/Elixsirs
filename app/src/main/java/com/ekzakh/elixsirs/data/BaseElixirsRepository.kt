package com.ekzakh.elixsirs.data

import com.ekzakh.elixsirs.domain.ElixirsDomain
import com.ekzakh.elixsirs.domain.ElixirsRepository

class BaseElixirsRepository(
    private val elixirsCloudDataSource: ElixirsCloudDataSource,
    private val mapper: ElixirsCloud.Mapper<ElixirsDomain>,
) : ElixirsRepository {
    override suspend fun elixirs(): ElixirsDomain {
        return elixirsCloudDataSource.elixirs().map(mapper)
    }
}
