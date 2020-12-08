package ru.maxdexter.mytranslatormvvm.di

import android.content.Context
import dagger.*
import ru.maxdexter.mytranslatormvvm.repository.Repository
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    internal fun provideRepository(context: Context): Repository = Repository(context)
}