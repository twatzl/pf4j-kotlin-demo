val pluginsDir: File by rootProject.extra


plugins {
    kotlin("jvm") // need to apply kotlin plugin here as it provides 'build' task
}

// here we define the tasks which will build the plugins in the subprojects
subprojects {
    // if the variable definitions are put here they are resolved for each subproject
    val pluginId: String by project
    val pluginClass: String by project
    val pluginProvider: String by project

    val project = this
    // we have to apply the gradle jvm plugin, because it provides the jar and build tasks
    apply(plugin = "org.jetbrains.kotlin.jvm")
    tasks {
        named("build") {
            dependsOn("plugin")
        }
        register<Jar>("plugin") {
            manifest {
                attributes["Plugin-Class"] = pluginClass
                attributes["Plugin-Id"] = pluginId
                attributes["Plugin-Version"] = archiveVersion
                attributes["Plugin-Provider"] = pluginProvider
            }
            archiveBaseName.set("plugin-$pluginId")
            into("classes") {
                with(named<Jar>("jar").get())
            }
            into("lib") {
                from({ sourceSets["main"].runtimeClasspath.filter { it.name.endsWith(".jar") } })
            }
            archiveExtension.set("zip")
        }
        register<Copy>("assemblePlugin") {
            from(project.tasks.named("plugin"))
            into(pluginsDir)
        }

    }
}

tasks.register<Copy>("assemblePlugins") {
    dependsOn(subprojects.map { it.tasks.named("assemblePlugin") })
}

tasks {
    "build" {
        dependsOn(named("assemblePlugins"))
    }
}
