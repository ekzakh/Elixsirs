package com.ekzakh.elixsirs.domain

import com.ekzakh.elixsirs.R
import com.ekzakh.elixsirs.presentation.ErrorUi
import com.ekzakh.elixsirs.presentation.Retry
import com.github.johnnysc.coremvvm.core.ManageResources
import com.github.johnnysc.coremvvm.domain.NoInternetConnectionException
import com.github.johnnysc.coremvvm.domain.ServiceUnavailableException
import com.github.johnnysc.coremvvm.presentation.adapter.ItemUi

interface DomainExceptionHandler {
    fun <T> map(mapper: Mapper<T>): T

    interface Mapper<T> {
        fun map(error: Exception): T

        class Base(
            private val resources: ManageResources,
            private val handleRetry: Retry
        ) : Mapper<ItemUi> {
            override fun map(error: Exception): ItemUi {
                val message = when (error) {
                    is NoInternetConnectionException -> resources.string(R.string.no_internet)
                    is ServiceUnavailableException -> resources.string(R.string.service_unavailable)
                    else -> resources.string(R.string.error)
                }
                return ErrorUi(message, handleRetry)
            }
        }
    }
}
