# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# Keep all classes that might be used via reflection
-keep class com.utilityapp.** { *; }

# Keep all model classes
-keep class com.utilityapp.data.local.entities.** { *; }

# Keep all view models
-keep class com.utilityapp.features.**.presentation.*ViewModel { *; }

# Keep all interfaces with @Keep annotation
-keep @androidx.annotation.Keep interface * { *; }

# Keep all classes with @Keep annotation
-keep @androidx.annotation.Keep class * { *; }

# Keep all methods with @Keep annotation
-keepclassmembers class * {
    @androidx.annotation.Keep *;
}

# Keep all Room database components
-keep class * extends androidx.room.RoomDatabase {
    public static <methods>;
}

# Keep all Room entities
-keep class * extends androidx.room.Entity {
    public static <methods>;
}

# Keep all Room DAOs
-keep class * extends androidx.room.Dao {
    public static <methods>;
}

# Keep all Room TypeConverters
-keep class * extends androidx.room.TypeConverter {
    public static <methods>;
}

# Keep Firebase classes
-keep class com.google.firebase.** { *; }
-keep class com.google.android.gms.** { *; }
-keep class com.google.api.** { *; }

# Keep ML Kit classes
-keep class com.google.mlkit.** { *; }

# Keep ZXing classes for QR scanning
-keep class com.journeyapps.barcodescanner.** { *; }
-keep class com.google.zxing.** { *; }