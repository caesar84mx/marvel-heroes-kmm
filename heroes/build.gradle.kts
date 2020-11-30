import build.Deps.Android
import build.Deps.Koin
import build.Deps.KotlinCore
import build.Deps.Android.Compose
import build.Deps.Misc
import build.Deps.Testing

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("androidx.navigation.safeargs")
}

android {
    compileSdkVersion(30)

    defaultConfig {
        applicationId = "com.caesar84mx.marvelheroes"
        minSdkVersion(28)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "0.1.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
        useIR = true
    }

    buildTypes {
        named("debug") {
            isDebuggable = true
        }
        named("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerVersion = "1.4.10"
        kotlinCompilerExtensionVersion = "1.0.0-alpha07"
    }

    lintOptions {
        isAbortOnError = false
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
        freeCompilerArgs = listOf("-Xallow-jvm-ir-dependencies", "-Xskip-prerelease-check",
            "-Xuse-experimental=kotlinx.coroutines.ExperimentalCoroutinesApi"
        )
    }
}

dependencies {
    implementation(project(":shared"))
    implementation(fileTree(Misc.fileTreeMap))
    implementation(KotlinCore.kotlinStdLib)
    implementation(KotlinCore.androidxCoreKtx)

    implementation(KotlinCore.Coroutines.coroutinesCore)
    implementation(KotlinCore.Coroutines.coroutinesAndroid)

    implementation(Android.Views.appcompat)
    implementation(Android.Views.material)

    implementation(Koin.android)
    implementation(Koin.androidxScope)
    implementation(Koin.androidxViewModel)

    implementation(Android.Navigation.fragmentKtx)
    implementation(Android.Navigation.uiKtx)

    implementation(Compose.core)
    implementation(Compose.layout)
    implementation(Compose.material)
    implementation(Compose.materialIconsExtended)
    implementation(Compose.tooling)
    implementation(Compose.runtime)
    implementation(Compose.runtimeLivedata)
    implementation(Compose.Accompanist.coil)

    testImplementation(Testing.jUnit)
    androidTestImplementation(Android.Testing.testExt)
    androidTestImplementation(Android.Testing.espresso)
    androidTestImplementation(Compose.uiTest)
}

