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
        maven {
            url = uri("https://npaw.jfrog.io/artifactory/youbora/")
        }
    }
}

rootProject.name = "CaixaForum"

include(":app")
include(":domain")
include(":data")
include(":localDataSource")
include(":remoteDataSource")
