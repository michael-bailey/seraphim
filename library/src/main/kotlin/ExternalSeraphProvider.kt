package net.michael_bailey

import java.io.File

abstract class ExternalSeraphProvider {

	protected val pluginFolder: File = File("./plugin")

	abstract fun getMatchers(): List<SeraphMatcher>

}