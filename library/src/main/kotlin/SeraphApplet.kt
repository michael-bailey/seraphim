package net.michael_bailey

import io.ktor.http.*

interface SeraphApplet {
	val name: String
	val description: String

	fun buildURL(input: Map<String, String>): Url
}