package ru.maxdexter.mytranslatormvvm.di

import android.app.Application
import android.content.Context
import dagger.*
import ru.maxdexter.mytranslatormvvm.repository.Repository
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    internal fun provideRepository(application: Application): Repository = Repository(application)
}