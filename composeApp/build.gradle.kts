import org.jetbrains.compose.desktop.application.dsl.TargetFormat

import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlinSerialization)
    // alias(libs.plugins.kotlinCocoapods)
    // alias(libs.plugins.kotlinxSerialization)
    // alias(libs.plugins.sqldelight)
    // kotlin("multiplatform")
    // kotlin("plugin.serialization") version "2.0.20"
    id("com.google.devtools.ksp") version "2.0.20-1.0.25"
    id("com.rickclephas.kmp.nativecoroutines") version "1.0.0-ALPHA-36"
}

kotlin {
    /*cocoapods {
        // Required properties
        // Specify the required Pod version here. Otherwise, the Gradle project version is used.
        version = "1.0"
        summary = "Upvote Kotlin/Native module"
        homepage = "upvote.dev"

        // Optional properties
        // Configure the Pod name here instead of changing the Gradle project name
        name = "UpvotePod"

        framework {
            // Required properties
            // Framework name configuration. Use this property instead of deprecated 'frameworkName'
            baseName = "UpvoteFramework"

            // Optional properties
            // Specify the framework linking type. It's dynamic by default.
            isStatic = false
            // Dependency export
            // export(project(":anotherKMMModule"))
            // transitiveExport = false // This is default.
        }

        // Maps custom Xcode configuration to NativeBuildType
        xcodeConfigurationToNativeBuildType["CUSTOM_DEBUG"] = NativeBuildType.DEBUG
        xcodeConfigurationToNativeBuildType["CUSTOM_RELEASE"] = NativeBuildType.RELEASE
        ios.deploymentTarget = "13.5"
        pod("GoogleMLKit/BarcodeScanning") {
            moduleName = "BarcodeScanning"
            version = "3.2.0"
        }
        podfile = project.file("../iosApp/Podfile")
    } */

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        moduleName = "composeApp"
        browser {
            val projectDirPath = project.projectDir.path
            commonWebpackConfig {
                outputFileName = "composeApp.js"
                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                    static = (static ?: mutableListOf()).apply {
                        // Serve sources to debug inside browser
                        add(projectDirPath)
                    }
                }
            }
        }
        binaries.executable()
    }

    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    jvm("desktop")

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
            // export(libs.decompose)
            // export(libs.lifecycle)
        }
    }

    /*iosArm64()
    cocoapods {
        iosArm64.deploymentTarget = "13.5"

        summary = "CocoaPods test library"
        homepage = "https://github.com/JetBrains/kotlin"

        pod("FirebaseAuth") {
            version = "10.16.0"
        }
    }*/

    sourceSets {
        val desktopMain by getting

        all {
            languageSettings.optIn("kotlinx.cinterop.ExperimentalForeignApi")
        }

        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.play.services.code.scanner)
            // implementation(libs.android.driver)
            implementation(libs.ktor.client.okhttp)
            // implementation(libs.ktor.client.android)
            implementation(libs.kotlinx.coroutines.android)
            runtimeOnly(libs.kotlin.reflect)
        }
        iosMain.dependencies {
            // implementation(libs.ktor.client.darwin)
            // implementation(libs.native.driver)
            implementation(libs.ktor.client.darwin)
            implementation(libs.lifecycle)
            runtimeOnly(libs.kotlin.reflect)
        }
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutines.swing)
            // implementation(libs.ktor.client.java)
            implementation(libs.ktor.client.cio)
            runtimeOnly(libs.kotlin.reflect)
        }
        /*jsMain.dependencies {
            // implementation(libs.ktor.client.js)
            implementation(libs.ktor.client.cio)
        }*/
        jsMain.dependencies {
            implementation(libs.ktor.client.js)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            // implementation(libs.androidx.lifecycle.viewmodel)
            // implementation(libs.androidx.lifecycle.runtime.compose)
            // runtimeOnly(libs.androidx.runtime)
            implementation(libs.coil.network.ktor3)
            // implementation(libs.openfoodfacts.kotlin)
            // implementation(libs.kotlinx.coroutines.core)
            // implementation(libs.ktor.client.core)
            // implementation(libs.ktor.client.content.negotiation)
            // implementation(libs.ktor.serialization.kotlinx.json)
            // implementation(libs.runtime)
            // implementation(libs.kotlinx.datetime)
            // implementation(libs.koin.core)
            implementation(libs.ktor.client.core)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.client.logging)
            implementation(libs.ktor.client.auth)
            implementation(libs.coil3.coil.compose)
            // implementation(libs.coil.network.ktor)
            /*
            implementation(libs.mvikotlin)
            implementation(libs.mvikotlin.main)
            implementation(libs.mvikotlin)
            implementation(libs.mvikotlin.extensions.coroutines)
            */
            // implementation(libs.mvikotlin.logging)
            // implementation(libs.mvikotlin.timetravel)
            // implementation(libs.vikotlin.extensions.coroutines)
            // api(libs.kmp.observableviewmodel.core)

            implementation(libs.decompose)
            implementation(libs.decompose.extensions.compose)
            /*
            implementation("org.jetbrains.compose.material3.adaptive:adaptive:1.0.0-alpha03")
            implementation("org.jetbrains.compose.material3.adaptive:adaptive-layout:1.0.0-alpha03")
            implementation("org.jetbrains.compose.material3.adaptive:adaptive-navigation:1.0.0-alpha03")
            */
            /*
            implementation("androidx.activity:activity-compose:1.10.0-alpha02") // 1.9.2
            implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.9.0-alpha04")
            */
            /*
            implementation("androidx.lifecycle:lifecycle-viewmodel:2.9.0-alpha04")
            */
            // runtimeOnly(libs.androidx.lifecycle.viewmodel.compose)
            implementation(libs.kotlinx.datetime)
            // runtimeOnly(libs.lifecycle.viewmodel)

            implementation(libs.jetbrains.lifecycle.viewmodel)
            implementation(libs.lifecycle.viewmodel.compose)

            /*implementation(libs.bom)
            implementation(libs.postgrest_kt)
            implementation(libs.auth_kt)
            implementation(libs.realtime_kt)*/
            /*
            implementation(platform("io.github.jan-tennert.supabase:bom:3.0.1"))
            implementation("io.github.jan-tennert.supabase:postgrest-kt")
            implementation("io.github.jan-tennert.supabase:auth-kt")
            implementation("io.github.jan-tennert.supabase:realtime-kt")
            */
            // implementation("io.github.agrevster:pocketbase-kotlin:2.6.3")
        }
    }
}

compose {
    resources {
        publicResClass = false
        packageOfResClass = "dev.upvote.resources"
        generateResClass = auto
    }
}

android {
    namespace = "dev.upvote"
    compileSdk = 35

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = "dev.upvote"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
    dependencies {
        debugImplementation(compose.uiTooling)
    }
}

compose.desktop {
    application {
        mainClass = "dev.upvote.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "dev.upvote"
            packageVersion = "1.0.0"
        }
    }
}
