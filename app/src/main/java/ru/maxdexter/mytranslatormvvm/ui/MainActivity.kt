package ru.maxdexter.mytranslatormvvm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import ru.maxdexter.mytranslatormvvm.R
import ru.maxdexter.mytranslatormvvm.adapter.MainAdapter
import ru.maxdexter.mytranslatormvvm.databinding.ActivityMainBinding
import ru.maxdexter.mytranslatormvvm.model.AppState
import ru.maxdexter.mytranslatormvvm.repository.Repository
import ru.maxdexter.mytranslatormvvm.ui.searchfragment.SearchFragment

class MainActivity: AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private var mainAdapter: MainAdapter? = null
    private val repository by lazy {
        Repository()
    }
    private val viewModel by lazy {
        ViewModelProvider(this,MainViewModelFactory(repository)).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        initFab()
        renderData()
        initRecycler()
    }

    private fun initRecycler() {
        mainAdapter = MainAdapter()
        binding.recycler.apply {
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun initFab() {
        binding.searchFab.setOnClickListener {
            val fragment = SearchFragment.newInstance()
            fragment.setClickListener {
                viewModel.getData(it, true)
            }
            supportFragmentManager.beginTransaction().add(fragment, "BOTTOM_SHEET")
                .commitAllowingStateLoss()

        }
    }

    private fun renderData() {
        viewModel.appState.observe(this, { appState ->
            when (appState) {
                is AppState.Success -> {
                    val result = appState.data
                    if (result == null || result.isEmpty()) {
                        showViewError()
                    }else  {
                        showViewSuccess()
                        mainAdapter?.setData(result)
                        mainAdapter?.setItemClickListener {

                        }
                    }
                }
                is AppState.Error -> {
                    showViewError()
                }
                is AppState.Loading -> {
                    showViewLoading()
                    binding.reloadButton.setOnClickListener {
                        recreate()
                    }
                }
            }

        })
    }


    private fun showViewError() {
        binding.successLinearLayout.visibility = android.view.View.GONE
        binding.loadingFrameLayout.visibility = android.view.View.GONE
        binding.errorLinearLayout.visibility = android.view.View.VISIBLE
    }


    private fun showViewSuccess() {
        binding.successLinearLayout.visibility = android.view.View.VISIBLE
        binding.loadingFrameLayout.visibility = android.view.View.GONE
        binding.errorLinearLayout.visibility = android.view.View.GONE
    }

    private fun showViewLoading() {
        binding.successLinearLayout.visibility = android.view.View.GONE
        binding.loadingFrameLayout.visibility = android.view.View.VISIBLE
        binding.errorLinearLayout.visibility = android.view.View.GONE
    }
}