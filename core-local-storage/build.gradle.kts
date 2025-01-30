plugins {
    id("com.streamplayer.kmp-library")
    id("com.google.devtools.ksp")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.bundles.room)
            implementation(libs.bundles.kotlin)
            implementation(libs.bundles.koin)
        }
    }
}

dependencies {
    add("kspCommonMainMetadata",libs.koin.ksp.compiler)
    add("kspAndroid", libs.koin.ksp.compiler)
}

ksp {
    arg("KOIN_CONFIG_CHECK","true")
}

dependencies {
    ksp(libs.roomCompiler)
}