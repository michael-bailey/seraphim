import org.gradle.jvm.tasks.Jar

plugins {
	idea

	kotlin("jvm") version libs.versions.kotlin apply false
}

// Copy the library JAR into the root-level plugins directory
val copyPlugin by tasks.registering(Copy::class) {
	val libJar =
		project(":british-information-technologies-seraph-applets").tasks.named<Jar>(
			"jar"
		)
	dependsOn(libJar)

	// Copy the single archive file produced by :library:jar
	from(libJar.flatMap { it.archiveFile })
	into(project(":app").layout.projectDirectory.dir("plugins"))
}

// Ensure the app build depends on the plugin being copied
gradle.projectsEvaluated {
	project(":app").tasks.matching { it.name == "run" }.configureEach {
		dependsOn(copyPlugin)
	}
}