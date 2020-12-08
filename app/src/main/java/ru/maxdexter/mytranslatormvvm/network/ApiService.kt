package ru.maxdexter.mytranslatormvvm.network

import retrofit2.http.GET
import retrofit2.http.Query
import ru.maxdexter.mytranslatormvvm.model.SearchResult
import java.util.*
import io.reactivex.Observable

interface ApiService {

    @GET("words/search")
    fun search(@Query("search") searchWord: String) : Observable<List<SearchResult>>
}