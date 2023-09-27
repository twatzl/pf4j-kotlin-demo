plugins {
  // See https://jmfayard.github.io/refreshVersions
  id("de.fayard.refreshVersions") version "0.60.2"
}

rootProject.name = "pf4j-kotlin-demo"

include("api")

include("app")

include("plugins")

include("plugins:plugin1")

include("plugins:plugin2")
