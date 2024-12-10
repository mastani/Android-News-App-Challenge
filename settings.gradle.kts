pluginManagement {
    includeBuild("build-logic")

    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        maven { setUrl("https://maven.myket.ir/") }
        google()
        mavenCentral()
    }
}

rootProject.name = "Android Challenge - News"
include(":app")
include(":common")
include(":designsystem")
include(":data")
include(":domain")
include(":features:home")
include(":features:search")
include(":features:archive")
include(":features:detail")
