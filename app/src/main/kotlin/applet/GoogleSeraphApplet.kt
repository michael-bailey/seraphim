package net.mi.applet

import io.ktor.http.*
import net.michael_bailey.utils.SeraphApplet

object GoogleSeraphApplet : SeraphApplet {
	override val name: String = "google"
	override val description: String = "Built-in applet for handling google requests"

	override fun buildURL(input: Map<String, String>): Url = URLBuilder().apply {
		protocol = URLProtocol.HTTPS
		host = "www.google.com"
		path("search")
		parameters.append("q", input["query"] ?: "Hello world")
	}.build()
}