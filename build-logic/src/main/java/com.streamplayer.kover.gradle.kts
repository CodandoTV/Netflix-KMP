plugins {
    id("org.jetbrains.kotlinx.kover")
}

koverReport {
    filters {
        excludes {
            packages(
                "*.di",
            )

            classes(
                "*.BuildConfig",
                "*.ComposableSingletons",
                "*ScreenKt*",
            )
            annotatedBy("Generated")
        }
    }
}