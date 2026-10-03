# Proguard & R8 rules for OmniPlay
# Optimized for minimum APK size while keeping full audio player stability

# Optimize bytecode aggressively
-optimizationpasses 5
-allowaccessmodification
-mergeinterfacesaggressively
-repackageclasses ""

# Strip Android logging in release builds to reduce size and improve performance
-assumenosideeffects class android.util.Log {
    public static boolean isLoggable(java.lang.String, int);
    public static int v(...);
    public static int d(...);
    public static int i(...);
    public static int w(...);
}

# Keep Application and Android Components
-keep public class * extends android.app.Application
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider

# Keep custom views if any are referenced in XML
-keep public class * extends android.view.View {
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
    public void set*(...);
}

# Keep Material Components and Support Library
-dontwarn com.google.android.material.**
-keep class com.google.android.material.** { *; }

# Keep MediaSessionCompat callbacks
-keep public class * extends androidx.media.MediaBrowserServiceCompat
-keep class android.support.v4.media.session.MediaSessionCompat$Callback { *; }
-keep class androidx.media.session.MediaButtonReceiver { *; }

# Keep Kotlin intrinsics and coroutines minimal
-dontwarn kotlin.**
-dontwarn kotlinx.coroutines.**

# Keep Models, Lyrics, and ViewBinding
-keep class xyz.omniplay.model.** { *; }
-keep class xyz.omniplay.lyrics.** { *; }
-keep class xyz.omniplay.databinding.** { *; }

# Strip debugging attributes
-renamesourcefileattribute SourceFile
-keepattributes SourceFile,LineNumberTable

# Keep Media3 / ExoPlayer
-keep class androidx.media3.** { *; }
-dontwarn androidx.media3.**
