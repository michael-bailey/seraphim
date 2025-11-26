package net.michael_bailey.utils

interface SeraphMatcher {
	val name: String

	val applet: SeraphApplet

	fun match(command: String): Boolean

	fun getArguments(command: String, fullCommand: String): Map<String, String>

}