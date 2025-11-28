package net.michael_bailey.external

import net.michael_bailey.ExternalSeraphProvider
import java.io.File
import java.net.URL
import java.net.URLClassLoader
import java.util.*


object ExternalAppletLoader {

	val pluginDir: File = File("plugins").apply {
		if (!exists() || !isDirectory) mkdir()
	}

	val urls =
		pluginDir.listFiles { directory, filename -> filename.endsWith(".jar") }
			.onEach { println("got jar: $it") }
			.map { f ->
				runCatching { f.toURI().toURL() }
			}.filter(Result<URL>::isSuccess)
			.mapNotNull(Result<URL>::getOrNull)
			.toTypedArray()

	val classLoader = URLClassLoader(urls, this::class.java.classLoader)

	// 2. Load service implementations from plugin classloader
	var loader: ServiceLoader<ExternalSeraphProvider> =
		ServiceLoader.load(ExternalSeraphProvider::class.java, classLoader)


	val providers =
		loader.onEach { println("got success provider: ${it.javaClass.simpleName}") }
			.toList()

	val matchers = providers.flatMap {
		it.getMatchers()
	}

}