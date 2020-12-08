package ru.maxdexter.mytranslatormvvm.di

import android.app.Application
import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import ru.maxdexter.mytranslatormvvm.ui.MainActivity
import javax.inject.Singleton

@Component(modules = [RepositoryModule::class,ViewModelModule::class,ActivityModule::class, AndroidSupportInjectionModule::class])
@Singleton
interface AppComponent {

    // Этот билдер мы вызовем из класса TranslatorApp, который наследует
    // Application
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        @BindsInstance
        fun context(context: Context): Builder
        fun build(): AppComponent
    }
    // Наш кастомный Application
    fun inject(app: TranslatorApp)

}