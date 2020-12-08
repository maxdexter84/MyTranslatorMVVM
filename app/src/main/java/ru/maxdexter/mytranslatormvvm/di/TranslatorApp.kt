package ru.maxdexter.mytranslatormvvm.di

import android.app.Activity
import android.app.Application
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.internal.SetFactory.builder
import javax.inject.Inject


class TranslatorApp : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): DispatchingAndroidInjector<Activity>? {
        return dispatchingAndroidInjector
    }

    override fun onCreate() {
        super.onCreate()
         DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)
    }
}