buildscript {
    repositories {
        google()
        mavenCentral()
        jcenter()
    }

    dependencies {
        classpath(build.Deps.GradlePlugins.sqlDelight)
        classpath(build.Deps.GradlePlugins.android)
        classpath(build.Deps.GradlePlugins.cocoapodsExt)
        classpath(build.Deps.GradlePlugins.serialization)
        classpath(build.Deps.GradlePlugins.kotlin)
        classpath(build.Deps.GradlePlugins.navigationSafeArgs)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        jcenter()
        maven(url = "https://kotlin.bintray.com/kotlinx")
        maven(url = "https://dl.bintray.com/touchlabpublic/kotlin")
        maven(url = "https://oss.sonatype.org/content/repositories/snapshots/")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

