package com.ekzakh.elixsirs.data

import com.ekzakh.elixsirs.domain.IngredientDomain
import com.google.gson.annotations.SerializedName

interface IngredientCloud {
    fun <T> map(mapper: Mapper<T>): T

    data class Base(
        @SerializedName("id")
        private val id: String,
        @SerializedName("name")
        private val name: String,
    ) : IngredientCloud {
        override fun <T> map(mapper: Mapper<T>): T = mapper.map(id, name)
    }

    interface Mapper<T> {
        fun map(id: String, name: String): T

        class Base : Mapper<IngredientDomain> {
            override fun map(id: String, name: String): IngredientDomain =
                IngredientDomain.Base(id, name)
        }
    }
}
