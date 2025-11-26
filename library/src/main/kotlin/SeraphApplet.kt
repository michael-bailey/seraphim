package net.michael_bailey.utils

import io.ktor.http.Url

interface SeraphApplet {
	val name: String
	val description: String

	fun buildURL(input: Map<String, String>): Url
}