package com.example.notedown.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFD0BCFF),             // Soft purple
    onPrimary = Color.Black,
    primaryContainer = Color(0xFF6650a4),    // Deeper container purple
    onPrimaryContainer = Color(0xFFEADDFF),

    secondary = Color(0xFFEFB8C8),           // Elegant pink
    onSecondary = Color.Black,
    secondaryContainer = Color(0xFF7D5260),
    onSecondaryContainer = Color(0xFFFFD8E4),

    background = Color(0xFF121212),          // Onyx black
    onBackground = Color(0xFFF1F1F1),         // Bright white text

    surface = Color(0xFF1E1E1E),             // Dark slate
    onSurface = Color(0xFFF1F1F1),

    surfaceVariant = Color(0xFF2C2C2E),      // Card surface
    onSurfaceVariant = Color(0xFFB3B3B3),

    outline = Color(0xFF3A3A3C)              // Graphite border
)


val LightColorScheme = lightColorScheme(
    primary = PurpleLight,
    onPrimary = Color.White,
    primaryContainer = BlushPinkLight,
    onPrimaryContainer = TextDark,

    secondary = PinkLight,
    onSecondary = Color.White,
    secondaryContainer = MistyLavenderLight,
    onSecondaryContainer = TextDark,

    background = NoteBackground,
    onBackground = TextDark,

    surface = SecondaryBackground,
    onSurface = TextDark,

    surfaceVariant = CloudGreyLight,
    onSurfaceVariant = SubtleText,

    outline = DividerGrey
)


@Composable
fun NoteDownTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}