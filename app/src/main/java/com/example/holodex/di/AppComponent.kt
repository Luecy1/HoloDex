package com.example.holodex.di

import android.content.Context
import com.example.holodex.App
import com.example.holodex.BuildConfig
import com.example.holodex.detail.HoloLiveDetailViewModelModule
import com.example.holodex.list.HoloLiveListViewModelModule
import com.example.holodex.repository.HoloLiveRepository
import com.example.holodex.repository.RemoteHoloLiveRepository
import com.example.holodex.repository.StreamRepository
import com.example.holodex.repository.StreamRepositoryImpl
import com.example.holodex.repository.api.*
import com.example.holodex.repository.preference.PreferenceService
import com.example.holodex.repository.preference.PreferenceServiceImpl
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
import twitter4j.Twitter
import twitter4j.TwitterFactory
import twitter4j.conf.ConfigurationBuilder
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ViewModelBuilder::class,
        HoloLiveListViewModelModule::class,
        HoloLiveDetailViewModelModule::class,
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
    ): HoloLiveRepository {
        return RemoteHoloLiveRepository(applicationContext, hololiveAPIService)
    }

    @Provides
    fun provideStreamInfoService(okhttp: OkHttpClient): StreamInfoService {
        return StreamInfoServiceImpl(okhttp)
    }

    @Provides
    fun provideStreamRepository(streamRepositoryImpl: StreamRepositoryImpl): StreamRepository {
        return streamRepositoryImpl
    }

    @Provides
    fun provideHttpClient(): OkHttpClient {
        val okhttp = OkHttpClient
            .Builder()
            .build()

        return okhttp
    }

    @Provides
    fun provideHoloLiverAPI(okhttp: OkHttpClient): HololiveAPIService {

        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://luecy1.github.io")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okhttp)
            .build()

        return retrofit.create(HololiveAPIService::class.java)
    }

    @Provides
    fun provideTwitterInstance(
        preferenceService: PreferenceService
    ): Twitter {

        val accessToken = preferenceService.getString("accessToken")

        @Suppress("ConstantConditionIf")
        val conf = if (BuildConfig.isCi.not()) {
            // for local env

            // setting for application-only authentication
            val conf = ConfigurationBuilder()
                .setApplicationOnlyAuthEnabled(true)
                .setOAuth2AccessToken(accessToken)
                .build()

            conf
        } else {
            // for github action env

            val consumerKey = BuildConfig.consumerKey
            val consumerSecret = BuildConfig.consumerSecret

            val conf = ConfigurationBuilder()
                .setApplicationOnlyAuthEnabled(true)
                .setOAuth2AccessToken(accessToken)
                .setOAuthConsumerKey(consumerKey)
                .setOAuthConsumerSecret(consumerSecret)
                .build()

            conf
        }

        return TwitterFactory(conf).instance
    }

    @Provides
    fun provideTwitterService(
        twitter: Twitter,
        preferenceService: PreferenceService
    ): TwitterService {
        return TwitterServiceImpl(twitter, preferenceService)
    }

    @Provides
    fun provideSharedPreference(sharedPreferences: PreferenceServiceImpl): PreferenceService {
        return sharedPreferences
    }
}