package com.ekzakh.elixsirs.data

import com.github.johnnysc.coremvvm.data.MakeService
import com.github.johnnysc.coremvvm.data.ProvideRetrofitBuilder

interface ProvideElixirsService {
    fun elixirService(): ElixirService

    class Base(retrofitBuilder: ProvideRetrofitBuilder) :
        MakeService.Abstract(retrofitBuilder),
        ProvideElixirsService {
        override fun baseUrl(): String = "https://wizard-world-api.herokuapp.com/"

        override fun elixirService(): ElixirService = service(ElixirService::class.java)
    }
}
