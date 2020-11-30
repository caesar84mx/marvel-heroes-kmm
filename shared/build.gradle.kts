import build.Versions
import build.Deps.Koin
import build.Deps.Persistence.SQLDelight
import build.Deps.Misc
import build.Deps.Logging
import build.Deps.Networking.Ktor
import build.Deps.Networking.Serializer
import build.Deps.KotlinCore
import build.Deps.Testing

plugins {
    id("com.squareup.sqldelight")
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("com.android.library")
    id("co.touchlab.native.cocoapods")
    id("kotlin-android-extensions")
}

android {
    compileSdkVersion(30)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].java.srcDirs(file("src/androidMain/kotlin"))
    sourceSets["main"].res.srcDirs(file("src/androidMain/res"))

    defaultConfig {
        minSdkVersion(28)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildTypes {
        getByName("debug") {
            isDebuggable = true
        }

        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

repositories {
    gradlePluginPortal()
    google()
    jcenter()
    mavenCentral()
}

kotlin {
    android()

    val onPhone = System.getenv("SDK_NAME")?.startsWith("iphoneos") ?: false
    if (onPhone) {
        iosArm64("ios")
    } else {
        iosX64("ios")
    }

    version = "1.0"

    allprojects {
        tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class.java).all {
            kotlinOptions {
                freeCompilerArgs.toMutableList().add("-Xopt-in=kotlin.RequiresOptIn")
            }
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common", Versions.KotlinCore.kotlin))
                implementation(SQLDelight.runtime)
                implementation(KotlinCore.Coroutines.coroutinesCore)
                implementation(KotlinCore.dateTime)
                implementation(Koin.core)
                Koin.test
                implementation(Serializer.Kotlinx.core)
                implementation(Misc.stately)
                implementation(Ktor.commonCore)
                implementation(Ktor.commonJson)
                implementation(Ktor.commonLogging)
                implementation(Ktor.commonSerialization)
                api(Logging.kermit)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(KotlinCore.androidxCoreKtx)
                implementation(KotlinCore.Coroutines.coroutinesAndroid)
                implementation(SQLDelight.driverAndroid)
                implementation(Ktor.jvmCore)
                implementation(Ktor.jvmJson)
                implementation(Ktor.jvmLogging)
                implementation(Ktor.androidSerialization)
                implementation(Ktor.cio)
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation(Testing.jUnit)
            }
        }
        val iosMain by getting {
            dependencies {
                implementation(Ktor.ios)
                implementation(SQLDelight.driverIos)
                implementation(KotlinCore.Coroutines.coroutinesCore) {
                    version {
                        strictly(Versions.KotlinCore.coroutines)
                    }
                }
                implementation(Koin.core)
            }
        }
        val iosTest by getting {}
    }

    cocoapodsext {
        summary = "Shared library for Heroes app"
        homepage = "https://github.com/caesar84mx"
        framework {
            isStatic = false
            export(Logging.kermit)
            transitiveExport = true
            freeCompilerArgs.toMutableList().add("-Xobjc-generics")
            linkerOpts.add("-lsqlite3")
        }
    }
}

sqldelight {
    database("MarvelHeroesDb") {
        packageName = "com.caesar84mx.shared.db"
    }
    linkSqlite = true
}