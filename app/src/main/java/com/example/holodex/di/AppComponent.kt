package com.example.holodex.di

import android.content.Context
import com.example.holodex.App
import com.example.holodex.list.HoloLiveListViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ViewModelBuilder::class,
        HoloLiveListViewModelModule::class,
        AndroidSupportInjectionModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }
}