package ru.maxdexter.mytranslatormvvm.ui

import android.app.Application
import android.app.job.JobScheduler
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import geekbrains.ru.translator.rx.SchedulerProvider
import ru.maxdexter.mytranslatormvvm.model.AppState
import ru.maxdexter.mytranslatormvvm.repository.Repository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import ru.maxdexter.mytranslatormvvm.model.SearchResult
import javax.inject.Inject


class MainViewModel @Inject constructor(private val repository: Repository):ViewModel (){ //Чтобы применить анотацию к конструктору надо явно указать ключевое слово constructor


    private val _appState = MutableLiveData<AppState>()
        val appState: LiveData<AppState>
        get() = _appState

    private val disposable = CompositeDisposable()
    fun getData(word: String, isOnline: Boolean){
        disposable.add(
            repository.getTranslate(word)
                .subscribeOn(SchedulerProvider().io())
                .observeOn(SchedulerProvider().io())
                .doOnSubscribe { _appState.value = AppState.Loading}
                .subscribeWith(getObserver())
        )

    }

    private fun getObserver(): DisposableObserver<List<SearchResult>> {
        return object : DisposableObserver<List<SearchResult>>() {
            override fun onNext(t: List<SearchResult>) {
                _appState.postValue(AppState.Success(t))
            }

            override fun onError(e: Throwable) {
                _appState.value = AppState.Error(e)
                Log.e("LOADING_ERROR", e.message.toString())
            }

            override fun onComplete() {

            }


        }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }


}