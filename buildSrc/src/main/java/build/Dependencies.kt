package build

object Versions {
    object KotlinCore {
        const val kotlin = "1.4.10"
        const val ktx = "1.5.0-alpha02"
        const val coroutines = "1.3.9-native-mt-2"
        const val dateTime = "0.1.0"
        const val cocoapodsExt = "0.11"
    }

    object Logging {
        const val kermit = "0.1.8"
    }

    object AndroidBasic {
        const val gradlePlugin = "4.2.0-alpha16"
        const val appcompat = "1.2.0-rc01"
        const val material = "1.2.1"
    }

    object Network {
        const val ktor = "1.4.1"
        const val kotlinx = "0.4.1"
        const val kotlinxSerializationCore = "1.0.0-RC"
    }

    object Persistence {
        const val sqlDelight = "1.4.3"
    }

    object Jetpack {
        const val navigation = "2.3.1"
        const val compose = "1.0.0-alpha07"
    }

    object Koin {
        const val koinAlpha = "3.0.1-alpha-2"
    }

    object Misc {
        const val stately = "1.1.0"
    }

    object Testing {
        const val junit = "4.13"
        const val junitTest = "1.1.2"
        const val espressoCore = "3.3.0"
    }
}

object Deps {
    object GradlePlugins {
        const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KotlinCore.kotlin}"
        const val serialization = "org.jetbrains.kotlin:kotlin-serialization:${Versions.KotlinCore.kotlin}"
        const val cocoapodsExt = "co.touchlab:kotlinnativecocoapods:${Versions.KotlinCore.cocoapodsExt}"
        const val android = "com.android.tools.build:gradle:${Versions.AndroidBasic.gradlePlugin}"
        const val navigationSafeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.Jetpack.navigation}"
        const val sqlDelight = "com.squareup.sqldelight:gradle-plugin:${Versions.Persistence.sqlDelight}"
    }

    object Koin {
        const val core = "org.koin:koin-core:${Versions.Koin.koinAlpha}"
        const val coreExt = "org.koin:koin-core-ext:${Versions.Koin.koinAlpha}"
        const val ktor = "org.koin:koin-ktor:${Versions.Koin.koinAlpha}"
        const val test = "testImplementation(\"org.koin:koin-test:${Versions.Koin.koinAlpha}\")"

        const val android = "org.koin:koin-android:${Versions.Koin.koinAlpha}"
        const val androidxScope = "org.koin:koin-androidx-scope:${Versions.Koin.koinAlpha}"
        const val androidxViewModel = "org.koin:koin-androidx-viewmodel:${Versions.Koin.koinAlpha}"
    }

    object KotlinCore {
        const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.KotlinCore.kotlin}"
        const val androidxCoreKtx = "androidx.core:core-ktx:${Versions.KotlinCore.ktx}"
        const val dateTime = "org.jetbrains.kotlinx:kotlinx-datetime:${Versions.KotlinCore.dateTime}"

        object Coroutines {
            const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.KotlinCore.coroutines}"
            const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.KotlinCore.coroutines}"
        }
    }

    object Persistence {
        object SQLDelight {
            const val runtime = "com.squareup.sqldelight:runtime:${Versions.Persistence.sqlDelight}"
            const val driverIos = "com.squareup.sqldelight:native-driver:${Versions.Persistence.sqlDelight}"
            const val driverAndroid = "com.squareup.sqldelight:android-driver:${Versions.Persistence.sqlDelight}"
        }
    }

    object Android {
        object Views {
            const val appcompat = "androidx.appcompat:appcompat:${Versions.AndroidBasic.appcompat}"
            const val material = "com.google.android.material:material:${Versions.AndroidBasic.material}"
        }

        object Compose {
            const val core = "androidx.compose.ui:ui:${Versions.Jetpack.compose}"
            const val layout = "androidx.compose.foundation:foundation-layout:${Versions.Jetpack.compose}"
            const val material = "androidx.compose.material:material:${Versions.Jetpack.compose}"
            const val materialIconsExtended = "androidx.compose.material:material-icons-extended:${Versions.Jetpack.compose}"
            const val runtime = "androidx.compose.runtime:runtime:${Versions.Jetpack.compose}"
            const val runtimeLivedata = "androidx.compose.runtime:runtime-livedata:${Versions.Jetpack.compose}"
            const val tooling = "androidx.ui:ui-tooling:${Versions.Jetpack.compose}"
            const val test = "androidx.compose.test:test-core:${Versions.Jetpack.compose}"
            const val uiTest = "androidx.ui:ui-test:${Versions.Jetpack.compose}"

            object Accompanist {
                private const val version = "0.3.3.1"
                const val coil = "dev.chrisbanes.accompanist:accompanist-coil:$version"
            }
        }

        object Navigation {
            const val fragmentKtx = "androidx.navigation:navigation-fragment-ktx:${Versions.Jetpack.navigation}"
            const val uiKtx = "androidx.navigation:navigation-ui-ktx:${Versions.Jetpack.navigation}"
        }

        object Testing {
            const val testExt = "androidx.test.ext:junit:${Versions.Testing.junitTest}"
            const val espresso = "androidx.test.espresso:espresso-core:${Versions.Testing.espressoCore}"
        }
    }

    object Networking {
        object Serializer {
            object Kotlinx {
                const val runtime = "org.jetbrains.kotlinx:kotlinx-serialization-runtime-common:${Versions.Network.kotlinx}"
                const val core = "org.jetbrains.kotlinx:kotlinx-serialization-core:${Versions.Network.kotlinxSerializationCore}"
            }
        }

        object Ktor {
            const val commonCore = "io.ktor:ktor-client-core:${Versions.Network.ktor}"
            const val commonJson = "io.ktor:ktor-client-json:${Versions.Network.ktor}"
            const val commonLogging = "io.ktor:ktor-client-logging:${Versions.Network.ktor}"
            const val commonSerialization ="io.ktor:ktor-client-serialization:${Versions.Network.ktor}"

            const val jvmCore = "io.ktor:ktor-client-core-jvm:${Versions.Network.ktor}"
            const val cio = "io.ktor:ktor-client-cio:${Versions.Network.ktor}"
            const val jvmJson = "io.ktor:ktor-client-json-jvm:${Versions.Network.ktor}"
            const val jvmLogging = "io.ktor:ktor-client-logging-jvm:${Versions.Network.ktor}"
            const val androidSerialization = "io.ktor:ktor-client-serialization-jvm:${Versions.Network.ktor}"

            const val ios = "io.ktor:ktor-client-ios:${Versions.Network.ktor}"
        }
    }

    object Logging {
        const val kermit = "co.touchlab:kermit:${Versions.Logging.kermit}"
    }

    object Misc {
        val fileTreeMap = mapOf("dir" to "libs", "include" to listOf("*.jar"))
        const val stately = "co.touchlab:stately-common:${Versions.Misc.stately}"
    }

    object Testing {
        const val jUnit = "junit:junit:${Versions.Testing.junit}"
    }
}