import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

import java.io.FileInputStream
import java.util.Properties

import com.github.jk1.license.render.JsonReportRenderer

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.gmazzo.buildconfig)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.jk1.license.report)
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinSerialization)
    id("com.google.devtools.ksp") version "2.0.21-1.0.26"
    id("com.rickclephas.kmp.nativecoroutines") version "1.0.0-ALPHA-37"
}

licenseReport {
    renderers = arrayOf(
        JsonReportRenderer("licenses.json")
    )
    outputDir = "${project.layout.projectDirectory}/reports/licenses"
}

tasks.register("prepareLicenseReport") {
    dependsOn("generateLicenseReport")
    doLast {
        copy {
            from("${project.layout.projectDirectory}/reports/licenses/licenses.json")
            into("src/commonMain/composeResources/files/json")
        }
        delete("${project.layout.projectDirectory}/reports")
    }
}

tasks.named("preBuild") {
    dependsOn("prepareLicenseReport")
}

fun getKeystoreProperties(): Properties {
    val keystorePropertiesFile = rootProject.file("keystore.properties")

    val keystoreProperties = Properties()
    if (keystorePropertiesFile.exists()) {
        keystoreProperties.load(FileInputStream(keystorePropertiesFile))
    } else {
        keystoreProperties.load(FileInputStream(rootProject.file("keystore.default.properties")))
    }
    return keystoreProperties
}

buildConfig {
    val keystoreProperties = getKeystoreProperties()

    packageName(libs.versions.app.packageName.get())
    buildConfigField("APP_VERSION", libs.versions.app.versionName.get())
    buildConfigField("APP_BUILD", libs.versions.app.versionCode.get())
    buildConfigField("ENCRYPTION_KEY", keystoreProperties["encryptionKey"] as String)
}

kotlin {
    androidTarget()
    jvm("desktop")
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
            binaryOption("bundleId", libs.versions.app.packageName.get())
            binaryOption("bundleVersion", libs.versions.app.versionCode.get())
            binaryOption("bundleShortVersionString", libs.versions.app.versionName.get())
            isStatic = true
            // export(libs.decompose)
            // export(libs.lifecycle)
        }
    }

    sourceSets {
        val desktopMain by getting

        all {
            languageSettings.optIn("kotlinx.cinterop.ExperimentalForeignApi")
        }

        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.androidx.datastore.core.android)
            implementation(libs.kotlinx.coroutines.android)
            implementation(libs.ktor.client.okhttp)
            implementation(libs.play.services.code.scanner)
            implementation(libs.multiplatform.settings.no.arg)
            runtimeOnly(libs.kotlin.reflect)

            implementation(libs.junit)
            implementation(libs.androidx.ui)
            implementation(libs.androidx.navigation.testing)
            implementation(libs.ui.test.manifest)
            implementation(libs.ui.test.junit4)

            implementation(libs.mockk)
            implementation(libs.mockk.android)
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
            implementation(libs.lifecycle)
            runtimeOnly(libs.kotlin.reflect)
        }
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutines.swing)
            implementation(libs.ktor.client.cio)
            // implementation(libs.androidx.datastore)
            // implementation(libs.androidx.datastore.core)
            // implementation(libs.androidx.datastore.core.jvm)
            implementation(libs.multiplatform.settings.no.arg)
            implementation(libs.androidx.datastore.core.jvm)
            runtimeOnly(libs.kotlin.reflect)
        }
        jsMain.dependencies {
            implementation(libs.ktor.client.js)
        }
        commonMain.dependencies {
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.material3)
            implementation(compose.runtime)
            implementation(compose.ui)

            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.datetime)

            implementation(libs.ktor.client.auth)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.logging)
            implementation(libs.ktor.serialization.kotlinx.json)

            implementation(libs.coil.network.ktor3)
            implementation(libs.coil3.coil.compose)

            implementation(libs.decompose)
            implementation(libs.decompose.extensions.compose)

            implementation(libs.jetbrains.lifecycle.viewmodel)
            implementation(libs.lifecycle.viewmodel.compose)

            implementation(libs.multiplatform.settings)
            implementation(libs.multiplatform.settings.coroutines)
            // implementation(libs.multiplatform.settings.datastore)

            implementation(libs.navigation.compose)
        }
        commonTest.dependencies {
            implementation(kotlin("test"))
            implementation(libs.kotlin.test)
        }
    }
}

compose {
    resources {
        publicResClass = false
        packageOfResClass = "dev.upvote.resources"
        generateResClass = auto
        customDirectory(
            sourceSetName = "desktopMain",
            directoryProvider = provider { layout.projectDirectory.dir("desktopResources") }
        )
    }
}

android {
    namespace = "dev.upvote"
    compileSdk = 35

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        namespace = libs.versions.app.packageName.get()
        applicationId = libs.versions.app.packageName.get()
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionName = libs.versions.app.versionName.get()
        versionCode = libs.versions.app.versionCode.get().toInt()
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1,LICENSE.md,LICENSE-notice.md}"
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }

        getByName("debug") {
            signingConfig = signingConfigs.getByName("debug")
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"

            resValue("string", "app_name", libs.versions.app.name.get() + " (Debug)")

            isMinifyEnabled = false
            isDebuggable = true
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
        mainClass = libs.versions.app.packageName.get() + libs.versions.app.mainName.get()

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)

            packageName = libs.versions.app.packageName.get()
            packageVersion = libs.versions.app.versionName.get()
            version = libs.versions.app.versionName.get()
            licenseFile.set(project.file("LICENSE-APACHE"))

            macOS {
                bundleID = libs.versions.app.packageName.get()
                packageName = libs.versions.app.name.get()
                packageVersion = libs.versions.app.versionName.get()
                dockName = libs.versions.app.name.get()

                //iconFile.set(rootProject.file("assets/icon.icns"))
                iconFile.set(rootProject.file("iosApp/iosApp/Assets.xcassets/icon.icns"))
            }

            windows {
                packageName = libs.versions.app.name.get()
                packageVersion = libs.versions.app.versionName.get()
                iconFile.set(rootProject.file("assets/icon.ico"))
            }

            linux {
                packageName = libs.versions.app.name.get()
                packageVersion = libs.versions.app.versionName.get()
                iconFile.set(rootProject.file("assets/icon.png"))
            }
        }
    }
}
