package ru.maxdexter.mytranslatormvvm.ui

import android.app.Application
import android.app.job.JobScheduler
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import geekbrains.ru.translator.rx.SchedulerProvider
import ru.maxdexter.mytranslatormvvm.model.AppState
import ru.maxdexter.mytranslatormvvm.repository.Repository
import io.reactivex.disposables.CompositeDisposable


class MainViewModel(private val repository: Repository):ViewModel (){


    private val _appState = MutableLiveData<AppState>()
        val appState: LiveData<AppState>
        get() = _appState

    private val disposable = CompositeDisposable()
    fun getData(word: String, isOnline: Boolean){
        disposable.add(
            repository.getTranslate(word)
                .subscribeOn(SchedulerProvider().io())
                .doOnSubscribe { _appState.postValue(AppState.Loading)}
                .doOnError {  _appState.postValue(AppState.Error(it) ) }
                .observeOn(SchedulerProvider().io())
                .subscribe{_appState.value = AppState.Success(it)}
        )

    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }


}