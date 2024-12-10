package app.mastani.news.data.di.component

import android.app.Application
import app.mastani.news.data.di.module.DispatcherModule
import app.mastani.news.data.di.module.NetworkModule
import app.mastani.news.data.di.module.RepositoryModule
import app.mastani.news.data.di.module.UseCaseModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class,
        RepositoryModule::class,
        UseCaseModule::class,
        DispatcherModule::class
    ]
)

interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }
}