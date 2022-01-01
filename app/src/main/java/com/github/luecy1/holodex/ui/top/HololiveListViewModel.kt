package com.github.luecy1.holodex.ui.top

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.luecy1.holodex.data.GenerationItem
import com.github.luecy1.holodex.data.Result
import com.github.luecy1.holodex.repository.HoloLiveRepository
import com.github.luecy1.holodex.repository.RemoteHoloLiveRepository
import com.github.luecy1.holodex.repository.api.HololiveAPIService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
@InstallIn(SingletonComponent::class)
class RemoteDataSourceModule {

    @Provides
    @Singleton
    fun provideUserRepository(remoteHoloLiveRepository: HololiveAPIService): RemoteHoloLiveRepository {
        return RemoteHoloLiveRepository(remoteHoloLiveRepository)
    }
}

@Module
@InstallIn(SingletonComponent::class)
object UserRepositoryModule {

    @Provides
    @Singleton
    fun provideUserRepository(remoteHoloLiveRepository: RemoteHoloLiveRepository): HoloLiveRepository {
        return remoteHoloLiveRepository
    }
}

@HiltViewModel
class HololiveListViewModel @Inject constructor(
    val repository: HoloLiveRepository
) : ViewModel() {

    sealed class UiState {
        object Initial : UiState()
        object Loading : UiState()
        data class Success(val data: List<GenerationItem>) : UiState()
        object Failure : UiState()
    }

    val uiState: MutableState<UiState> = mutableStateOf(UiState.Initial)

    fun initData(forceLoad: Boolean = false) {
        viewModelScope.launch {
            uiState.value = UiState.Loading

            val result = repository.getHoloLiveList(forceLoad)
            when (result) {
                is Result.Success<List<GenerationItem>> -> {
                    uiState.value = UiState.Success(result.data)
                }
                is Result.Error -> {
                    uiState.value = UiState.Failure
                }
            }
        }
    }
}
