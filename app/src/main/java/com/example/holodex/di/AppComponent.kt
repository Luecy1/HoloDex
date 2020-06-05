package com.example.holodex.di

import android.content.Context
import com.example.holodex.App
import com.example.holodex.list.HoloLiveListViewModelModule
import com.example.holodex.repository.HoloLiverRepository
import com.example.holodex.repository.RemoteHoloLiverRepository
import com.example.holodex.repository.api.HololiveAPIService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
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
    fun provideHoloLiverRepository(
        applicationContext: Context,
        hololiveAPIService: HololiveAPIService
    ): HoloLiverRepository {
        return RemoteHoloLiverRepository(applicationContext, hololiveAPIService)
    }

//    @Provides
//    fun provideHoloLiverRepository(applicationContext: Context): HoloLiverRepository {
//        return HoloLiverRepositoryImpl(applicationContext)
//    }

    @Provides
    fun provideHoloLiverAPI(): HololiveAPIService {

        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        val okhttp = OkHttpClient
            .Builder()
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://luecy1.github.io")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okhttp)
            .build()

        return retrofit.create(HololiveAPIService::class.java)
    }
}