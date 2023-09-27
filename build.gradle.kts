val pluginsDir by extra { file("${layout.buildDirectory.get()}/plugins") }

plugins {
    alias(libs.plugins.org.jetbrains.kotlin.jvm)
}

allprojects {
    repositories {
        mavenCentral()
    }
}

tasks.named("build") {
    dependsOn(":app:shadowJar")
}
