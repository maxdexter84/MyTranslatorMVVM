package ru.maxdexter.mytranslatormvvm.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.maxdexter.mytranslatormvvm.ui.MainActivity

@Component(modules = [RepositoryModule::class])
interface AppComponent {

    // Фабрика для создания экземпляров AppComponent
    @Component.Factory
    interface Factory{
        // С помощью @BindsInstance переданный контекст будет доступен на графе
        // @BindsInstance сообщает Dagger, что ему нужно добавить этот экземпляр в график, и всякий раз, когда требуется контекст, предоставьте этот экземпляр.
        fun create(@BindsInstance context: Context): AppComponent
    }
    fun inject(activity: MainActivity)
}