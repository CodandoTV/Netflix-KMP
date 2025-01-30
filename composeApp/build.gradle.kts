@file:Suppress("UnstableApiUsage")

plugins {
    id("com.streamplayer.application")
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.compose.compiler)
}
kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.featureListStreams)
            implementation(projects.featureDetail)
            implementation(projects.featureSearch)
            implementation(projects.featureProfile)
            implementation(projects.coreShared)
            implementation(projects.coreSharedUi)
            implementation(projects.coreNavigation)
            implementation(projects.coreNetworking)
            implementation(projects.coreLocalStorage)

            implementation(libs.navigation.compose)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.preview)
            implementation(libs.bundles.koin)
            implementation(libs.bundles.androidSupport)
            implementation(libs.bundles.kotlin)
            implementation(libs.lottie)
        }
    }
}

dependencies {
    // Kover - Combined report
    rootProject.subprojects.forEach { kover(it) }
}