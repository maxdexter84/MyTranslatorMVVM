package ru.maxdexter.mytranslatormvvm.model

import com.google.gson.annotations.SerializedName

data class SearchResult(
    @field:SerializedName("text") val text: String?,
    @field:SerializedName("meanings") val meanings: List<Meanings>?
)
