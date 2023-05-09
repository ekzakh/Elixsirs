package com.ekzakh.elixsirs.sl

import com.ekzakh.elixsirs.data.BaseElixirsRepository
import com.ekzakh.elixsirs.data.ElixirCloud
import com.ekzakh.elixsirs.data.ElixirsCloud
import com.ekzakh.elixsirs.data.ElixirsCloudDataSource
import com.ekzakh.elixsirs.data.IngredientCloud
import com.ekzakh.elixsirs.data.ProvideElixirsService
import com.ekzakh.elixsirs.domain.DomainExceptionHandler
import com.ekzakh.elixsirs.domain.ElixirDomain
import com.ekzakh.elixsirs.domain.ElixirsDomain
import com.ekzakh.elixsirs.domain.ElixirsInteractor
import com.ekzakh.elixsirs.domain.IngredientDomain
import com.ekzakh.elixsirs.presentation.ChangeExpanded
import com.ekzakh.elixsirs.presentation.ElixirsCommunications
import com.ekzakh.elixsirs.presentation.ElixirsViewModel
import com.ekzakh.elixsirs.presentation.Retry
import com.github.johnnysc.coremvvm.domain.HandleDomainError
import com.github.johnnysc.coremvvm.sl.CoreModule
import com.github.johnnysc.coremvvm.sl.Module

class MainModule(private val core: CoreModule) : Module<ElixirsViewModel.Base> {
    override fun viewModel(): ElixirsViewModel.Base {
        val repository = BaseElixirsRepository(
            elixirsCloudDataSource = ElixirsCloudDataSource.Base(
                ProvideElixirsService.Base(
                    core,
                ).elixirService(),
                HandleDomainError(),
            ),
            mapper = ElixirsCloud.Mapper.Base(ElixirCloud.Mapper.Base(IngredientCloud.Mapper.Base()))
        )

        var viewModel: ElixirsViewModel = ElixirsViewModel.Empty()
        val changeExpanded = object : ChangeExpanded {
            override fun changeExpanded(elixirId: String) {
                viewModel.changeExpanded(elixirId)
            }
        }
        val retry = object : Retry {
            override fun retry() {
                viewModel.retry()
            }
        }
        viewModel = ElixirsViewModel.Base(
            interactor = ElixirsInteractor.Base(
                mapper = ElixirsDomain.Mapper.Base(
                    ElixirDomain.Mapper.ToElixirUi(changeExpanded),
                    ElixirDomain.Mapper.ToIngredientsUi(IngredientDomain.Mapper.Base())
                ),
                repository = repository,
                dispatchers = core.dispatchers(),
                errorHandler = DomainExceptionHandler.Mapper.Base(core, retry)
            ),
            communications = ElixirsCommunications.Base(),
            dispatchers = core.dispatchers()
        )
        return viewModel
    }
}
