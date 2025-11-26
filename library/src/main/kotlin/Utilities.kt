@file:OptIn(ExperimentalTime::class)

package net.michael_bailey.utils

import kotlinx.serialization.Serializable
import kotlinx.coroutines.*
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@Serializable
class Printer(val message: String) {
	fun printMessage() = runBlocking {
		val now: Instant = Clock.System.now()
		launch {
			delay(1000L)
			println(now.toString())
		}
		println(message)
	}
}