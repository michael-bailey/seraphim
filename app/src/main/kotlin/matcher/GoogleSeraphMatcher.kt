package net.michael_bailey.matcher

import net.michael_bailey.SeraphApplet
import net.michael_bailey.SeraphMatcher
import net.michael_bailey.applet.GoogleSeraphApplet

object GoogleSeraphMatcher: SeraphMatcher {
	override val name: String = this::class.qualifiedName.toString()
	override val applet: SeraphApplet = GoogleSeraphApplet

	val commandStrings = listOf("g", "google")
	val argRegex = Regex("""^(?:g|google)\s+(.+)""")

	override fun match(
		command: String,
	): Boolean = commandStrings.contains(command)

	override fun getArguments(
		command: String,
		fullCommand: String,
	): Map<String, String> {
		val groups = argRegex.matchEntire(fullCommand)

		return mapOf(
			"query" to (groups?.groups[1]?.value ?: "Hello world")
		)

	}
}