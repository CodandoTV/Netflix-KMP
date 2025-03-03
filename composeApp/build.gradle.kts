@file:Suppress("UnstableApiUsage")

plugins {
    id("com.streamplayer.application")
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.compose.compiler)
}
kotlin {
    sourceSets {
        androidMain.dependencies {
            implementation(libs.koin.android)
            implementation(libs.lottie)
            implementation(compose.preview)
        }
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
            implementation(compose.components.resources)

            implementation(libs.koin.core)
        }
    }
}

dependencies {
    // Kover - Combined report
    rootProject.subprojects.forEach { kover(it) }
}