package com.github.luecy1.holodex.repository.api.xml


class XmlParseException(
    msg: String?,
    exception: Exception
) : RuntimeException(msg, exception) {
    constructor(exception: Exception) : this(null, exception)
}