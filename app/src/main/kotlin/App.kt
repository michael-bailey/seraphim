package net.mi

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.application.install
import io.ktor.server.html.respondHtml
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.response.respondRedirect
import io.ktor.server.routing.Routing
import io.ktor.server.routing.*
import io.ktor.server.routing.routing
import kotlinx.html.body
import kotlinx.html.h1
import kotlinx.html.head
import kotlinx.html.p
import kotlinx.html.title
import javax.naming.spi.Resolver

fun Application.module() {
	install(Routing)
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
					p { +"Use the /resolve?target=example command to redirect." }
				}
			}
		}

		get("/resolve") {
			val target = call.request.queryParameters["target"]
				?: throw IllegalArgumentException("Missing 'target' parameter")

			call.respondRedirect("https://www.google.co.uk", permanent = false)
		}
	}
}