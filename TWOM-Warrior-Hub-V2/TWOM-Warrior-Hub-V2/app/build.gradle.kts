plugins {
    id("com.android.application")
}

android {
    namespace = "com.twom.warriorhub"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.twom.warriorhub"
        minSdk = 26
        targetSdk = 28
        versionCode = 1
        versionName = "1.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}
