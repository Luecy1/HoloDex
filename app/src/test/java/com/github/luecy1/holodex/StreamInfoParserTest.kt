package com.github.luecy1.holodex

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.luecy1.holodex.repository.api.xml.StreamInfoParser
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [28]) // API level 29 is not available
class StreamInfoParserTest {

    private val parser = StreamInfoParser()

    @Test
    fun feedParseTest() {

        val inputStream = javaClass.classLoader?.getResourceAsStream("feed.xml")!!
        val feed = parser.parse(inputStream)

        assertThat(feed).isNotNull()
        assertThat(feed.entryList.size).isEqualTo(3)
        assertThat(feed.entryList[0].title).isEqualTo("テストタイトル１")
        assertThat(feed.entryList[0].mediaGroup.description).isEqualTo(
            "\n" +
                    "                テストディスクリプション\n" +
                    "            "
        )
        assertThat(feed.entryList[1].title).isEqualTo("テスト タイトル２")
        assertThat(feed.entryList[1].mediaGroup.description).isEqualTo(
            "\n" +
                    "                テスト文章\n" +
                    "            "
        )

        assertThat(feed.entryList[2].title).isEqualTo("テストタイトル３")
        assertThat(feed.entryList[2].mediaGroup.description).isEqualTo(
            "\n" +
                    "                テスト\n" +
                    "                ディスクリプション\n" +
                    "            "
        )

    }
}
