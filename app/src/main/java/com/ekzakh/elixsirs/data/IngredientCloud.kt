package com.ekzakh.elixsirs.data

import com.google.gson.annotations.SerializedName

interface IngredientCloud {
    interface Mapper<T> {
        fun map(id: String, name: String): T
    }

    fun <T> map(mapper: Mapper<T>): T

    data class Base(
        @SerializedName("id")
        private val id: String,
        @SerializedName("name")
        private val name: String,
    ) : IngredientCloud {
        override fun <T> map(mapper: Mapper<T>): T = mapper.map(id, name)
    }
}
