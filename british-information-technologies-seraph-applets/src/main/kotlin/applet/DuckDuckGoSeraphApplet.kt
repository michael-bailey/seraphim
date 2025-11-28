package net.michael_bailey.applet

import io.ktor.http.*
import net.michael_bailey.SeraphApplet

object DuckDuckGoSeraphApplet : SeraphApplet {
	override val name: String = "duckduckgo"
	override val description: String =
		"Built-in applet for handling DuckDuckGo search requests"

	override fun buildURL(input: Map<String, String>): Url = URLBuilder().apply {
		protocol = URLProtocol.HTTPS
		host = "duckduckgo.com"
		parameters.append("q", input["query"] ?: "Hello world")
	}.build()
}
