package com.ekzakh.elixsirs.domain

import com.ekzakh.elixsirs.R
import com.ekzakh.elixsirs.presentation.ElixirsUi
import com.ekzakh.elixsirs.presentation.ErrorUi
import com.github.johnnysc.coremvvm.core.ManageResources
import com.github.johnnysc.coremvvm.domain.NoInternetConnectionException
import com.github.johnnysc.coremvvm.domain.ServiceUnavailableException

interface DomainExceptionHandler {
    fun <T> map(mapper: Mapper<T>): T

    interface Mapper<T> {
        fun map(error: Exception): T

        class Base(private val resources: ManageResources) : Mapper<ElixirsUi> {
            override fun map(error: Exception): ElixirsUi {
                val message = when (error) {
                    is NoInternetConnectionException -> resources.string(R.string.no_internet)
                    is ServiceUnavailableException -> resources.string(R.string.service_unavailable)
                    else -> resources.string(R.string.error)
                }
                return ElixirsUi.Error(ErrorUi(message = message))
            }
        }
    }
}
