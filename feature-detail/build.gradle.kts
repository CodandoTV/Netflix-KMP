@file:Suppress("UnstableApiUsage")

plugins {
    id("com.streamplayer.kmp-library")
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.koin.core)
            implementation(projects.coreNetworking)
            implementation(projects.coreNavigation)
            implementation(projects.coreShared)
            implementation(projects.coreSharedUi)
            implementation(projects.coreLocalStorage)

            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.preview)
            implementation(libs.navigation.compose)
            implementation(libs.bundles.koin)
            implementation(libs.bundles.networking)
            implementation(libs.coil)
            implementation(libs.bundles.androidSupport)
        }
        commonTest.dependencies {
            implementation(libs.bundles.test)
        }
    }
}