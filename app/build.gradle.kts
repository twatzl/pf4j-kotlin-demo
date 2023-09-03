import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

val pf4jVersion: String by project
val pluginsDir: File by rootProject.extra
val appMainClass = "org.pf4j.kotlindemo.BootKt"

plugins {
    alias(libs.plugins.com.github.johnrengelman.shadow)
    kotlin("jvm")
    application
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(project(":api"))
    implementation(libs.log4j.api)
    implementation(libs.log4j.core)
    implementation(libs.slf4j.log4j12)
    implementation(libs.pf4j)
    implementation(libs.commons.lang3)
}

application { mainClass.set(appMainClass) }

tasks.named<JavaExec>("run") { systemProperty("pf4j.pluginsDir", pluginsDir.absolutePath) }

tasks {
    named<ShadowJar>("shadowJar"){
        archiveClassifier.set("uber")
        archiveBaseName.set("${project.name}-plugin-demo")
    }
}
