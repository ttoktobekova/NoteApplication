plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    id("androidx.navigation.safeargs.kotlin") version "2.8.2" apply false
    id("com.google.devtools.ksp") version "2.0.20-1.0.24" apply false
    id("com.google.gms.google-services") version "4.4.2" apply false

}