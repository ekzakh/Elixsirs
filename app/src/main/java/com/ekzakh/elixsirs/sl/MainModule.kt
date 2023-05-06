package com.ekzakh.elixsirs.sl

import com.ekzakh.elixsirs.data.BaseElixirsRepository
import com.ekzakh.elixsirs.data.ElixirCloud
import com.ekzakh.elixsirs.data.ElixirsCloud
import com.ekzakh.elixsirs.data.ElixirsCloudDataSource
import com.ekzakh.elixsirs.data.IngredientCloud
import com.ekzakh.elixsirs.data.ProvideElixirsService
import com.ekzakh.elixsirs.domain.ElixirDomain
import com.ekzakh.elixsirs.domain.ElixirsDomain
import com.ekzakh.elixsirs.domain.ElixirsInteractor
import com.ekzakh.elixsirs.domain.IngredientDomain
import com.ekzakh.elixsirs.presentation.ElixirsCommunications
import com.ekzakh.elixsirs.presentation.ElixirsViewModel
import com.github.johnnysc.coremvvm.domain.HandleDomainError
import com.github.johnnysc.coremvvm.sl.CoreModule
import com.github.johnnysc.coremvvm.sl.Module

class MainModule(private val core: CoreModule) : Module<ElixirsViewModel.Base> {
    override fun viewModel(): ElixirsViewModel.Base =
        ElixirsViewModel.Base(
            interactor = ElixirsInteractor.Base(
                mapper = ElixirsDomain.Mapper.Base(
                    ElixirDomain.Mapper.ToElixirUi(),
                    ElixirDomain.Mapper.ToIngredientsUi(IngredientDomain.Mapper.Base())
                ),
                repository = BaseElixirsRepository(
                    elixirsCloudDataSource = ElixirsCloudDataSource.Base(
                        ProvideElixirsService.Base(
                            core,
                        ).elixirService(),
                        HandleDomainError(),
                    ),
                    mapper = ElixirsCloud.Mapper.Base(ElixirCloud.Mapper.Base(IngredientCloud.Mapper.Base()))
                ),
                dispatchers = core.dispatchers(),
                handleError = HandleDomainError()
            ),
            communications = ElixirsCommunications.Base(),
            progressCommunication = core.provideProgressCommunication(),
            dispatchers = core.dispatchers()
        )
}
