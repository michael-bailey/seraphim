plugins {
//	alias(libs.) apply false
}

dependencyResolutionManagement {
	@Suppress("UnstableApiUsage")
	repositories {
		mavenCentral()
	}
}


include(":library")
include(":app")

rootProject.name = "seraphim"