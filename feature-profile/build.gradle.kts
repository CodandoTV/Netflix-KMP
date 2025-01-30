@file:Suppress("UnstableApiUsage")

plugins {
    id("com.streamplayer.kmp-library")
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.coreNetworking)
            implementation(projects.coreNavigation)
            implementation(projects.coreShared)
            implementation(projects.coreSharedUi)
            implementation(libs.navigation.compose)
            implementation(compose.material3)
            implementation(compose.ui)

            implementation(libs.bundles.koin)
            api(libs.koin.annotations)
            implementation(libs.bundles.networking)
            implementation(libs.bundles.androidSupport)
            implementation(libs.coil)

        }
    }
}