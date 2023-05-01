package com.ekzakh.elixsirs.domain

import com.ekzakh.elixsirs.presentation.ElixirUi
import com.ekzakh.elixsirs.presentation.ElixirsUi
import com.ekzakh.elixsirs.presentation.IngredientUi
import org.junit.Assert.assertEquals
import org.junit.Test

class ElixirsDomainTest {

    private val mapper: ElixirsDomain.Mapper<ElixirsUi> = ElixirsDomain.Mapper.Base(
        ElixirDomain.Mapper.ToElixirUi(),
        ElixirDomain.Mapper.ToIngredientsUi(
            IngredientDomain.Mapper.Base(),
        ),
    )

    @Test
    fun `test domain to ui`() {
        val elixirs = ElixirsDomain.Base(
            listOf(
                ElixirDomain.Base(
                    "id",
                    "name",
                    "effect",
                    listOf(
                        IngredientDomain.Base("ing1Id", "ing1"),
                        IngredientDomain.Base("ing2Id", "ing2"),
                    ),
                ),
            ),
        )

        val expected = ElixirsUi.Base(
            listOf(
                ElixirUi("id", "name", "effect", false),
                IngredientUi("ing1Id", "ing1"),
                IngredientUi("ing2Id", "ing2"),
            ),
        )
        val actual = elixirs.map(mapper)
        assertEquals(expected, actual)
    }
}
