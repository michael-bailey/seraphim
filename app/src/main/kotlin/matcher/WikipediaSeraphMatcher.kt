package net.mi.matcher

import net.mi.applet.WikipediaSeraphApplet
import net.michael_bailey.utils.SeraphApplet
import net.michael_bailey.utils.SeraphMatcher

object WikipediaSeraphMatcher : SeraphMatcher {
	override val name: String = this::class.qualifiedName.toString()
	override val applet: SeraphApplet = WikipediaSeraphApplet

	private val commandStrings = listOf("w", "wiki", "wikipedia")
	private val argRegex = Regex("""^(?:w|wiki|wikipedia)\s+(.+)""")

	override fun match(command: String): Boolean =
		commandStrings.contains(command)

	override fun getArguments(
		command: String,
		fullCommand: String,
	): Map<String, String> {
		val groups = argRegex.matchEntire(fullCommand)

		return mapOf(
			"query" to (groups?.groups?.get(1)?.value ?: "Hello world")
		)
	}
}
