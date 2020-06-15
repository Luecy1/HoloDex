package com.github.luecy1.holodex.repository.api.xml

import android.util.Xml
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import java.io.IOException
import java.io.InputStream

class StreamInfoParser {

    private val ns: String? = null

    fun parse(inputStream: InputStream): Feed {
        try {
            val parser: XmlPullParser = Xml.newPullParser()
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false)
            parser.setInput(inputStream, null)
            parser.nextTag()
            return readFeed(parser)
        } catch (e: IOException) {
            throw RuntimeException(e)
        } catch (e: XmlPullParserException) {
            throw RuntimeException(e)
        }
    }

    private fun readFeed(parser: XmlPullParser): Feed {
        val entries = mutableListOf<Entry>()

        parser.require(XmlPullParser.START_TAG, ns, "feed")
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.eventType != XmlPullParser.START_TAG) {
                continue
            }
            if (parser.name == "entry") {
                readEntry(parser)?.let {
                    entries.add(it)
                }
            } else {
                skip(parser)
            }
        }
        return Feed(entries)
    }

    private fun readEntry(parser: XmlPullParser): Entry? {
        parser.require(XmlPullParser.START_TAG, ns, "entry")
        var title: String? = null
        var mediaGroup: MediaGroup? = null
        var link: String? = null
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.eventType != XmlPullParser.START_TAG) {
                continue
            }
            when (parser.name) {
                "title" -> title = readInnerText(parser, "title")
                "media:group" -> mediaGroup = readMediaGroup(parser)
                "link" -> link = readLink(parser)
                else -> skip(parser)
            }
        }

        title ?: return null
        mediaGroup ?: return null
        link ?: return null

        return Entry(title, mediaGroup, link)
    }

    private fun readInnerText(parser: XmlPullParser, tagName: String): String {
        parser.require(XmlPullParser.START_TAG, ns, tagName)
        val title = readText(parser)
        parser.require(XmlPullParser.END_TAG, ns, tagName)
        return title
    }

    private fun readLink(parser: XmlPullParser): String {
        var link = ""
        parser.require(XmlPullParser.START_TAG, ns, "link")
        val tag = parser.name
        val relType = parser.getAttributeValue(null, "rel")
        if (tag == "link") {
            if (relType == "alternate") {
                link = parser.getAttributeValue(null, "href")
                parser.nextTag()
            }
        }
        parser.require(XmlPullParser.END_TAG, ns, "link")
        return link
    }

    private fun readMediaGroup(parser: XmlPullParser): MediaGroup? {
        parser.require(XmlPullParser.START_TAG, ns, "media:group")

        var title: String? = null
        var thumbnail: String? = null
        var description: String? = null

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.eventType != XmlPullParser.START_TAG) {
                continue
            }
            when (parser.name) {
                "media:title" -> title = readInnerText(parser, "media:title")
                "media:thumbnail" -> thumbnail = readThumbnail(parser)
                "media:description" -> description = readInnerText(parser, "media:description")
                else -> skip(parser)
            }
        }

        title ?: return null
        thumbnail ?: return null
        description ?: return null

        return MediaGroup(title, thumbnail, description)
    }

    private fun readThumbnail(parser: XmlPullParser): String {
        for (index in 0 until parser.attributeCount) {

            if ("url" == parser.getAttributeName(index)) {
                val thumbnail = parser.getAttributeValue(index)
                parser.nextTag()
                return thumbnail
            }
        }
        return ""
    }

    private fun readText(parser: XmlPullParser): String {
        var result = ""
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.text
            parser.nextTag()
        }
        return result
    }

    private fun skip(parser: XmlPullParser) {
        if (parser.eventType != XmlPullParser.START_TAG) {
            throw IllegalStateException()
        }
        var depth = 1
        while (depth != 0) {
            when (parser.next()) {
                XmlPullParser.END_TAG -> depth--
                XmlPullParser.START_TAG -> depth++
            }
        }
    }

}
