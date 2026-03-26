plugins {
    java
}

group = "dev.trick"
version = "1.0"
description = "GriefPrevention integration for squaremap"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://jitpack.io")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.20.6-R0.1-SNAPSHOT")
    compileOnly("com.github.TechFortress:GriefPrevention:17.0.0")
    compileOnly("xyz.jpenilla:squaremap-api:1.2.2")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

tasks.processResources {
    filteringCharset = "UTF-8"
    filesMatching("plugin.yml") {
        expand(
            "name" to project.name,
            "version" to project.version,
            "description" to (project.description ?: "")
        )
    }
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
    options.release.set(21)
}
