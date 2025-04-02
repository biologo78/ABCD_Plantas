plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.ajfm.abcd_plantas"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.ajfm.abcd_plantas"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }


    allprojects {
        repositories {
            maven {
                url = uri("https://www.jitpack.io")
            }
        }

        buildscript {
            repositories {
                maven { url = uri("https://www.jitpack.io") }
            }
        }
    }
    dependencies {

        implementation(libs.appcompat)
        implementation(libs.material)
        implementation(libs.activity)
        implementation(libs.constraintlayout)
        testImplementation(libs.junit)
        androidTestImplementation(libs.ext.junit)
        androidTestImplementation(libs.espresso.core)

        androidTestImplementation(libs.junit.v115)
        androidTestImplementation(libs.espresso.core.v351)

        implementation(libs.sqliteassethelper)

        implementation(libs.picasso)
        implementation(libs.sqliteassethelper)
        implementation(libs.activity.ktx)
        implementation(libs.fragment)
        implementation(libs.fragment.ktx)
        implementation(libs.photoview)
        implementation(libs.viewpager2)
        implementation(libs.glide)
    }
}
dependencies {
    implementation(libs.appcompat.v161)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    implementation(libs.activity)
    implementation(libs.recyclerview)
}