package ru.maxdexter.mytranslatormvvm.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.maxdexter.mytranslatormvvm.ui.MainActivity

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}