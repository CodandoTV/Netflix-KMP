package extensions

import org.gradle.api.artifacts.VersionCatalog

internal fun VersionCatalog.getLibrary(library: String) = findLibrary(library).get()
internal fun VersionCatalog.getVersion(library: String) = findVersion(library).get()
internal fun VersionCatalog.getBundle(bundle: String) = findBundle(bundle).get()

internal fun VersionCatalog.koinCoreDependency() = findLibrary("koin_core").get()

internal fun VersionCatalog.koinAnnotationsDependency() = findLibrary("koin_annotations").get()

internal fun VersionCatalog.koinComposeDependency() = findLibrary("koin_compose").get()

internal fun VersionCatalog.koinCompiler() = findLibrary("koin_ksp_compiler").get()

