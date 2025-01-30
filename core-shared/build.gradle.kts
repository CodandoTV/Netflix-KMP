plugins {
    id("com.streamplayer.kmp-library")
}


kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.bundles.koin)
        }
    }
}