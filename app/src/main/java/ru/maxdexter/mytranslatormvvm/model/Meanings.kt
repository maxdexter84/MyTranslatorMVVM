package ru.maxdexter.mytranslatormvvm.model

import com.google.gson.annotations.SerializedName

data class Meanings(
    @field:SerializedName("translation") val translation: Translation?,
    @field:SerializedName("imageUrl") val imageUrl: String?
)
