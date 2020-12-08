package ru.maxdexter.mytranslatormvvm.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.maxdexter.mytranslatormvvm.repository.Repository
import javax.inject.Inject
import javax.inject.Singleton


class MainViewModelFactory constructor(private val repository: Repository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}