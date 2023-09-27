plugins {
    kotlin("kapt")
}

val pf4jVersion: String by project

dependencies {
    compileOnly(project(":api"))
    compileOnly(kotlin("stdlib"))

    compileOnly(libs.pf4j)
    kapt(libs.pf4j)
    implementation(libs.commons.lang3) // this is an example for an external library included
}



