package com.ekzakh.elixsirs.domain

import com.ekzakh.elixsirs.R
import com.ekzakh.elixsirs.presentation.ElixirsState
import com.ekzakh.elixsirs.presentation.ErrorUi
import com.ekzakh.elixsirs.presentation.Retry
import com.github.johnnysc.coremvvm.core.ManageResources
import com.github.johnnysc.coremvvm.domain.NoInternetConnectionException
import com.github.johnnysc.coremvvm.domain.ServiceUnavailableException

interface DomainExceptionHandler {
    fun <T> map(mapper: Mapper<T>): T

    interface Mapper<T> {
        fun map(error: Exception): T

        class Base(
            private val resources: ManageResources,
            private val handleRetry: Retry
        ) : Mapper<ElixirsState> {
            override fun map(error: Exception): ElixirsState {
                val message = when (error) {
                    is NoInternetConnectionException -> resources.string(R.string.no_internet)
                    is ServiceUnavailableException -> resources.string(R.string.service_unavailable)
                    else -> resources.string(R.string.error)
                }
                return ElixirsState.Error(ErrorUi(message, handleRetry))
            }
        }
    }
}
