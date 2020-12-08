package ru.maxdexter.mytranslatormvvm.repository

import android.content.Context
import ru.maxdexter.mytranslatormvvm.model.AppState
import ru.maxdexter.mytranslatormvvm.network.Retrofit
import io.reactivex.Observable
import ru.maxdexter.mytranslatormvvm.model.SearchResult
import java.util.*
import javax.inject.Inject

class Repository @Inject constructor(val context: Context) {


    fun getTranslate(word: String): Observable<List<SearchResult>>{

       return Retrofit.api.search(word)

    }
}