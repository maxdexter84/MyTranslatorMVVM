package ru.maxdexter.mytranslatormvvm.repository

import ru.maxdexter.mytranslatormvvm.model.AppState
import ru.maxdexter.mytranslatormvvm.network.Retrofit
import io.reactivex.Observable
import ru.maxdexter.mytranslatormvvm.model.SearchResult
import java.util.*

class Repository {


    fun getTranslate(word: String): Observable<List<SearchResult>>{

       return Retrofit.api.search(word)

    }
}