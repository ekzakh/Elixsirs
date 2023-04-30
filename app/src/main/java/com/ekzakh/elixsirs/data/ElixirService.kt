package com.ekzakh.elixsirs.data

import retrofit2.http.GET

interface ElixirService {

    @GET("Elixirs")
    suspend fun elixirs(): List<ElixirCloud.Base>
}
