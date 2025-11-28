package net.michael_bailey

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.html.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.html.*
import net.michael_bailey.external.ExternalAppletLoader
import net.michael_bailey.matcher.GoogleSeraphMatcher
import net.michael_bailey.matcher.WikipediaSeraphMatcher

fun main(args: Array<String>) {
	embeddedServer(Netty, module = { module() }, port = 8080).start(true)
}

fun Application.module() {

	val matcherIndex = listOf<SeraphMatcher>(
		GoogleSeraphMatcher,
		WikipediaSeraphMatcher
	)

	install(StatusPages) {
		exception<Throwable> { call, cause ->
			call.respondHtml(HttpStatusCode.InternalServerError) {
				head { title { +"Something went wrong" } }
				body {
					h1 { +"Error occurred" }
					p { +(cause.message ?: "Unexpected error") }
				}
			}
		}
	}

	routing {
		get("/") {
			call.respondHtml {
				head { title { +"Landing" } }
				body {
					h1 { +"Welcome" }
					p { +"Use the /resolve?target=g Hello world" }
				}
			}
		}

		get("/resolve") {
			val target = call.request.queryParameters["target"]
				?: throw IllegalArgumentException("Missing 'target' parameter")

			val command = target.split(" ").first()

			val matcher = matcherIndex.first {
				it.match(command)
			}

			val args = matcher.getArguments(command, target)

			val result = matcher.applet.buildURL(args)

			call.respondRedirect(result, permanent = false)
		}
	}
}