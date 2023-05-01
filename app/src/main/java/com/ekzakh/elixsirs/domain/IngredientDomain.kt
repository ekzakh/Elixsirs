package com.ekzakh.elixsirs.domain

import com.ekzakh.elixsirs.presentation.IngredientUi

interface IngredientDomain {
    interface Mapper<T> {
        fun map(id: String, name: String): T

        class Base : Mapper<IngredientUi> {
            override fun map(id: String, name: String): IngredientUi = IngredientUi(id, name)
        }
    }

    fun <T> map(mapper: Mapper<T>): T

    data class Base(
        private val id: String,
        private val name: String,
    ) : IngredientDomain {
        override fun <T> map(mapper: Mapper<T>): T = mapper.map(id, name)
    }
}
