import extensions.koinAnnotationsDependency
import extensions.koinCompiler
import extensions.koinComposeDependency
import extensions.koinCoreDependency
import extensions.libs
import org.jetbrains.kotlin.gradle.dsl.KotlinCompile

plugins {
    id("com.google.devtools.ksp")
    id("kotlin-multiplatform")
}

kotlin {
    sourceSets {
        commonMain {
            kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")

            dependencies {
                implementation(libs.koinCoreDependency())
                api(libs.koinAnnotationsDependency())
                api(libs.koinComposeDependency())
            }
        }
    }
}

dependencies {
    add("kspCommonMainMetadata", libs.koinCompiler())
    add("kspAndroid", libs.koinCompiler())
//    add("kspIosX64", libs.koin.ksp.compiler)
//    add("kspIosArm64", libs.koin.ksp.compiler)
//    add("kspIosSimulatorArm64", libs.koin.ksp.compiler)
}

// WORKAROUND: ADD this dependsOn("kspCommonMainKotlinMetadata") instead of above dependencies
tasks.withType<KotlinCompile<*>>().configureEach {
    if (name != "kspCommonMainKotlinMetadata") {
        dependsOn("kspCommonMainKotlinMetadata")
    }
}

afterEvaluate {
    tasks.filter {
        it.name.contains("SourcesJar", true)
    }.forEach {
        println("SourceJarTask====>${it.name}")
        it.dependsOn("kspCommonMainKotlinMetadata")
    }
}

ksp {
    arg("KOIN_CONFIG_CHECK", "false")
}