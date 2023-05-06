package com.ekzakh.elixsirs.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStoreOwner
import com.ekzakh.elixsirs.databinding.ActivityMainBinding
import com.ekzakh.elixsirs.presentation.adapter.ElixirsAdapter
import com.github.johnnysc.coremvvm.sl.ProvideViewModel

class MainActivity : AppCompatActivity(), ProvideViewModel {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: ElixirsViewModel.Base

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = provideViewModel(ElixirsViewModel.Base::class.java, this)

        val elixirsAdapter = ElixirsAdapter.Elixirs()
        binding.recyclerView.adapter = elixirsAdapter

        viewModel.observe(this) { elixirsUi ->
            elixirsUi.map(elixirsAdapter)
        }

        viewModel.observeProgress(this) { visibility ->
            visibility.apply(binding.progress)
        }
    }

    override fun <T : ViewModel> provideViewModel(clazz: Class<T>, owner: ViewModelStoreOwner): T =
        (application as ProvideViewModel).provideViewModel(clazz, owner)
}
