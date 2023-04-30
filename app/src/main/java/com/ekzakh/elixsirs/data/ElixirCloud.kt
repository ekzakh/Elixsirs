package com.ekzakh.elixsirs.data

import com.google.gson.annotations.SerializedName

interface ElixirCloud {

    fun <T> map(mapper: Mapper<T>): T

    interface Mapper<T> {
        fun map(id: String, name: String, ingredients: List<IngredientCloud>): T
    }

    data class Base(
        @SerializedName("id")
        private val id: String,
        @SerializedName("name")
        private val name: String,
        @SerializedName("effect")
        private val effect: String,
        @SerializedName("ingredients")
        private val ingredients: List<IngredientCloud.Base>,
    ) : ElixirCloud {
        override fun <T> map(mapper: Mapper<T>): T = mapper.map(id, name, ingredients)
    }
}
