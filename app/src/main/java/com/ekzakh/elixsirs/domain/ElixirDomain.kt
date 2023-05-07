package com.ekzakh.elixsirs.domain

import com.ekzakh.elixsirs.presentation.ChangeExpanded
import com.ekzakh.elixsirs.presentation.ElixirUi
import com.ekzakh.elixsirs.presentation.IngredientUi

interface ElixirDomain {

    fun <T> map(mapper: Mapper<T>): T
    data class Base(
        private val id: String,
        private val name: String,
        private val effect: String,
        private val ingredients: List<IngredientDomain>,
    ) : ElixirDomain {
        override fun <T> map(mapper: Mapper<T>): T = mapper.map(id, name, effect, ingredients)
    }

    interface Mapper<T> {
        fun map(id: String, name: String, effect: String, ingredients: List<IngredientDomain>): T

        class ToElixirUi(private val changeExpanded: ChangeExpanded) : Mapper<ElixirUi> {
            override fun map(
                id: String,
                name: String,
                effect: String,
                ingredients: List<IngredientDomain>,
            ): ElixirUi = ElixirUi.Base(id, name, effect, false, changeExpanded)
        }

        class ToIngredientsUi(private val mapper: IngredientDomain.Mapper<IngredientUi>) : Mapper<List<IngredientUi>> {
            override fun map(
                id: String,
                name: String,
                effect: String,
                ingredients: List<IngredientDomain>,
            ): List<IngredientUi> = ingredients.map { it.map(mapper) }
        }
    }
}
