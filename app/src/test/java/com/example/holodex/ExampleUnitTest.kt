package com.example.holodex

import com.example.holodex.di.AppModule
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking

import org.junit.Test

class ExampleUnitTest {

    val hololiveAPI = AppModule.provideHoloLiverAPI()

    @Test
    fun ときのそら_取得できる() {
        runBlocking {
            val members = hololiveAPI.hololiveMembers()
            assertThat(members.first().name)
                .isEqualTo("ときのそら")
        }
    }
}
