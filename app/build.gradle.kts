plugins {
    id("com.android.application")
}

android {
    namespace = "com.mkandeel.gallery"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.mkandeel.gallery"
        minSdk = 24
        targetSdk = 34
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
        buildFeatures {
            viewBinding = true
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    val nav_version = "2.7.6"
    implementation ("androidx.fragment:fragment:1.6.2")
    // Navigation component
    implementation ("androidx.navigation:navigation-fragment:$nav_version")

    implementation ("androidx.navigation:navigation-ui:$nav_version")
    implementation ("androidx.lifecycle:lifecycle-extensions:2.2.0")

    //noinspection LifecycleAnnotationProcessorWithJava8
    annotationProcessor ("androidx.lifecycle:lifecycle-compiler:2.6.2")

    implementation ("com.intuit.sdp:sdp-android:1.1.0")
    implementation ("com.intuit.ssp:ssp-android:1.1.0")
    implementation ("androidx.preference:preference:1.2.1")
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.retrofit2:converter-scalars:2.9.0")
    implementation ("com.squareup.okhttp3:okhttp:4.11.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.11.0")
    implementation ("com.github.MikeOrtiz:TouchImageView:3.6")

    implementation ("io.reactivex.rxjava3:rxandroid:3.0.2")
    // Because RxAndroid releases are few and far between, it is recommended you also
    // explicitly depend on RxJava's latest version for bug fixes and new features.
    // (see https://github.com/ReactiveX/RxJava/releases for latest 3.x.x version)
    implementation ("io.reactivex.rxjava3:rxjava:3.1.8")
    implementation ("com.github.akarnokd:rxjava3-retrofit-adapter:3.0.0")
    implementation ("com.github.bumptech.glide:glide:4.16.0")
}