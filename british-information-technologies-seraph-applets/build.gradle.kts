plugins {
	kotlin("jvm") version libs.versions.kotlin
}

group = "net.michael_bailey"

dependencies {
	implementation(project(":library"))
}

tasks.test {
	useJUnitPlatform()
}
