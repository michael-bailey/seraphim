package net.mi.applet

import io.ktor.http.*
import net.michael_bailey.utils.SeraphApplet

object WikipediaSeraphApplet : SeraphApplet {
	override val name: String = "wikipedia"
	override val description: String =
		"Built-in applet for handling Wikipedia searches"

	override fun buildURL(input: Map<String, String>): Url = URLBuilder().apply {
		protocol = URLProtocol.HTTPS
		host = "en.wikipedia.org"
		// Use MediaWiki search endpoint
		path("w", "index.php")
		parameters.append("search", input["query"] ?: "Hello world")
	}.build()
}
