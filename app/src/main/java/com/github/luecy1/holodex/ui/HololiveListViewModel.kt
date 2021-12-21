package com.github.luecy1.holodex.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.luecy1.holodex.Event
import com.github.luecy1.holodex.R
import com.github.luecy1.holodex.data.GenerationItem
import com.github.luecy1.holodex.data.HololiverItem
import com.github.luecy1.holodex.data.Result
import com.github.luecy1.holodex.repository.HoloLiveRepository
import com.github.luecy1.holodex.repository.RemoteHoloLiveRepository
import com.github.luecy1.holodex.repository.api.HololiveAPIService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class ApiClientModule {

    @Provides
    @Singleton
    fun provideApiClient(): HololiveAPIService {

        val okhttp = OkHttpClient
            .Builder()
            .build()

        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://luecy1.github.io/HoloDexBackEnd/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okhttp)
            .build()

        return retrofit.create(HololiveAPIService::class.java)
    }
}

@Module
@InstallIn(ViewModelComponent::class)
class RemoteDataSourceModule {
    @Provides
    fun provideUserRepository(remoteHoloLiveRepository: HololiveAPIService): RemoteHoloLiveRepository {
        return RemoteHoloLiveRepository(remoteHoloLiveRepository)
    }
}

@Module
@InstallIn(ViewModelComponent::class)
class UserRepositoryModule {

    @Provides
    fun provideUserRepository(remoteHoloLiveRepository: RemoteHoloLiveRepository): HoloLiveRepository {
        return remoteHoloLiveRepository
    }
}

@HiltViewModel
class HololiveListViewModel @Inject constructor(
    private val repository: HoloLiveRepository
) : ViewModel() {

    private val _openHololiver = MutableLiveData<Event<HololiverItem>>()
    val openHololiver: LiveData<Event<HololiverItem>> = _openHololiver

    private val _errorMessage = MutableLiveData<Event<Int>>()
    val errorMessage: LiveData<Event<Int>> = _errorMessage

    private val _hololiveList = MutableLiveData<List<GenerationItem>>()
    val hololiveList: LiveData<List<GenerationItem>> = _hololiveList

    private val _loading = MutableLiveData<Boolean>(false)
    val loading: LiveData<Boolean> = _loading


    fun initData(forceLoad: Boolean = false) {

        viewModelScope.launch {
            _loading.value = true

            when (val holoLiveListResult = repository.getHoloLiveList(forceLoad)) {
                is Result.Success<List<GenerationItem>> -> {
                }
                is Result.Error -> {
                    _hololiveList.value = emptyList()
                    _errorMessage.value = Event(R.string.error_message)
                }
            }
            _loading.value = false
        }
    }

    fun onClickHololiver(hololiverItem: HololiverItem) {
        _openHololiver.postValue(Event(hololiverItem))
    }
}
