package com.example.holodex.di

import android.content.Context
import com.example.holodex.App
import com.example.holodex.list.HoloLiveListViewModelModule
import com.example.holodex.repository.HoloLiverRepository
import com.example.holodex.repository.HoloLiverRepositoryImpl
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ViewModelBuilder::class,
        HoloLiveListViewModelModule::class,
        AppModule::class,
        AndroidSupportInjectionModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }
}

@Module
object AppModule {

    @Provides
    fun provideHoloLiverRepository(applicationContext: Context): HoloLiverRepository {
        return HoloLiverRepositoryImpl(applicationContext)
    }
}