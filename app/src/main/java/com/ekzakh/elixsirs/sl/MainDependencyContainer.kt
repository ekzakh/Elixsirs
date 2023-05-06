package com.ekzakh.elixsirs.sl

import androidx.lifecycle.ViewModel
import com.ekzakh.elixsirs.presentation.ElixirsViewModel
import com.github.johnnysc.coremvvm.sl.CoreModule
import com.github.johnnysc.coremvvm.sl.DependencyContainer
import com.github.johnnysc.coremvvm.sl.Module

class MainDependencyContainer(
    private val dependencyContainer: DependencyContainer,
    private val coreModule: CoreModule,
) : DependencyContainer {

    override fun <T : ViewModel> module(clazz: Class<T>): Module<*> =
        if (clazz == ElixirsViewModel.Base::class.java) {
            MainModule(coreModule)
        } else {
            dependencyContainer.module(clazz)
        }
}
