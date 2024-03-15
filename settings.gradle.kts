pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "NewsSearchApp"
include(":app")
include(":newsapi")
include(":database")
include(":newsmain")
include(":news-main")
include(":mainnews")
include(":freatures")
