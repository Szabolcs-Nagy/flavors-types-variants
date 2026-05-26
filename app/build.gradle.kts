plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.coding.flavors_types_variants"
    compileSdk {
        version = release(36) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        applicationId = "com.coding.flavors_types_variants"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    // Two independent axes that combine into the final flavor name (for example demoSandbox).
    flavorDimensions += listOf("tier", "environment")
    productFlavors {
        // Tier dimension: controls feature/business tier.
        create("demo") {
            dimension = "tier"
            applicationIdSuffix = ".demo"
            versionNameSuffix = "-demo"
            buildConfigField("String", "TIER_FLAVOR", "\"demo\"")
        }
        create("full") {
            dimension = "tier"
            applicationIdSuffix = ".full"
            versionNameSuffix = "-full"
            buildConfigField("String", "TIER_FLAVOR", "\"full\"")
        }

        // Environment dimension: controls backend/runtime environment.
        create("sandbox") {
            dimension = "environment"
            applicationIdSuffix = ".sandbox"
            versionNameSuffix = "-sandbox"
            buildConfigField("String", "ENVIRONMENT_FLAVOR", "\"sandbox\"")
        }
        create("production") {
            dimension = "environment"
            buildConfigField("String", "ENVIRONMENT_FLAVOR", "\"production\"")
        }
    }

    buildTypes {
        // Local/dev builds with debug tooling enabled.
        debug {
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"
            buildConfigField("String", "BUILD_TYPE_LABEL", "\"debug\"")
        }

        // Pre-release test builds; currently based on debug behavior.
        create("staging") {
            initWith(getByName("debug"))
            applicationIdSuffix = ".staging"
            versionNameSuffix = "-staging"
            matchingFallbacks += listOf("debug")
            buildConfigField("String", "BUILD_TYPE_LABEL", "\"staging\"")
        }

        // Production-oriented build.
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "BUILD_TYPE_LABEL", "\"release\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        // Required because flavor/build-type metadata is exposed via BuildConfig fields.
        buildConfig = true
        compose = true
    }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    debugImplementation(libs.androidx.compose.ui.tooling)
}