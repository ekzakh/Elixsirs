package com.ekzakh.elixsirs.domain

interface ElixirsRepository {
    suspend fun elixirs(): ElixirsDomain
}
