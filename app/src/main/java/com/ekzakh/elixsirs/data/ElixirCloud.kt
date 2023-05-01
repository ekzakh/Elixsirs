package com.ekzakh.elixsirs.data

import com.ekzakh.elixsirs.domain.ElixirDomain
import com.ekzakh.elixsirs.domain.IngredientDomain
import com.google.gson.annotations.SerializedName

interface ElixirCloud {

    fun <T> map(mapper: Mapper<T>): T

    interface Mapper<T> {
        fun map(id: String, name: String, effects: String, ingredients: List<IngredientCloud>): T

        class Base(private val mapper: IngredientCloud.Mapper<IngredientDomain>) :
            Mapper<ElixirDomain> {
            override fun map(
                id: String,
                name: String,
                effects: String,
                ingredients: List<IngredientCloud>,
            ): ElixirDomain =
                ElixirDomain.Base(id, name, effects, ingredients.map { it.map(mapper) })
        }
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
        override fun <T> map(mapper: Mapper<T>): T = mapper.map(id, name, effect, ingredients)
    }
}
