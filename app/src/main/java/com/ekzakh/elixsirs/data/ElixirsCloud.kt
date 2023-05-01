package com.ekzakh.elixsirs.data

import com.ekzakh.elixsirs.domain.ElixirDomain
import com.ekzakh.elixsirs.domain.ElixirsDomain

interface ElixirsCloud {
    fun <T> map(mapper: Mapper<T>): T

    class Base(private val elixirs: List<ElixirCloud>) : ElixirsCloud {
        override fun <T> map(mapper: Mapper<T>): T = mapper.map(elixirs)
    }

    interface Mapper<T> {
        fun map(elixirs: List<ElixirCloud>): T

        class Base(private val mapper: ElixirCloud.Mapper<ElixirDomain>) : Mapper<ElixirsDomain> {
            override fun map(elixirs: List<ElixirCloud>): ElixirsDomain {
                val domainElixirs = elixirs.map { it.map(mapper) }
                return ElixirsDomain.Base(domainElixirs)
            }
        }
    }
}
