plugins {
	// Apply the shared build logic from a convention plugin.
	// The shared code is located in `buildSrc/src/main/kotlin/kotlin-jvm.gradle.kts`.

	kotlin("jvm") version libs.versions.kotlin

	// Apply Kotlin Serialization plugin from `gradle/libs.versions.toml`.
	alias(libs.plugins.kotlinPluginSerialization)
}

dependencies {
	// Apply the kotlinx bundle of dependencies from the version catalog (`gradle/libs.versions.toml`).
	implementation(libs.bundles.kotlinxEcosystem)

	implementation(libs.bundles.ktor.server)

	testImplementation(kotlin("test"))
}