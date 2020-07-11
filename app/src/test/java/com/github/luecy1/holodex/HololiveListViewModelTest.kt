package com.github.luecy1.holodex

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.luecy1.holodex.data.GenerationItem
import com.github.luecy1.holodex.data.HoloLiveGeneration
import com.github.luecy1.holodex.data.HololiverItem
import com.github.luecy1.holodex.data.Result
import com.github.luecy1.holodex.list.HololiveListViewModel
import com.github.luecy1.holodex.repository.HoloLiveRepository
import com.github.luecy1.holodex.util.MainCoroutineRule
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class HololiveListViewModelTest {

    lateinit var viewmodel: HololiveListViewModel

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    val generationItem = listOf(
        GenerationItem(
            1, HoloLiveGeneration.First, listOf(
                HololiverItem(
                    0,
                    "ときのそら",
                    "",
                    "",
                    emptyList(),
                    "",
                    "",
                    ""
                )
            )
        )
    )

    @Before
    fun before() {

        val repository = mockk<HoloLiveRepository>()

        coEvery { repository.getHoloLiveList(any()) } returns Result.Success(generationItem)

        viewmodel = HololiveListViewModel(repository)
    }

    @Test
    fun success() {
        viewmodel.hololiveList.observeForever {
            Truth.assertThat(it).isEqualTo(generationItem)
        }
    }
}