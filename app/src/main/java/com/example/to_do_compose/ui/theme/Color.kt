package com.example.to_do_compose.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.to_do_compose.utils.Constants.HALF_ALPHA

val md_theme_light_primary = Color(0xFF006a68)
val md_theme_light_onPrimary = Color(0xFFFFFFFF)
val md_theme_light_primaryContainer = Color(0xFF6FF7F3)
val md_theme_light_onPrimaryContainer = Color(0xFF00201F)
val md_theme_light_secondary = Color(0xFF345CA8)
val md_theme_light_onSecondary = Color(0xFFFFFFFF)
val md_theme_light_secondaryContainer = Color(0xFFD9E2FF)
val md_theme_light_onSecondaryContainer = Color(0xFF001A43)
val md_theme_light_tertiary = Color(0xFF006E10)
val md_theme_light_onTertiary = Color(0xFFFFFFFF)
val md_theme_light_tertiaryContainer = Color(0xFF7EFE74)
val md_theme_light_onTertiaryContainer = Color(0xFF002202)
val md_theme_light_error = Color(0xFFBA1A1A)
val md_theme_light_errorContainer = Color(0xFFFFDAD6)
val md_theme_light_onError = Color(0xFFFFFFFF)
val md_theme_light_onErrorContainer = Color(0xFF410002)
val md_theme_light_background = Color(0xFFF2FFFD)
val md_theme_light_onBackground = Color(0xFF00201F)
val md_theme_light_surface = Color(0xFFF2FFFD)
val md_theme_light_onSurface = Color(0xFF00201F)
val md_theme_light_surfaceVariant = Color(0xFFDAE5E3)
val md_theme_light_onSurfaceVariant = Color(0xFF3F4948)
val md_theme_light_outline = Color(0xFF6F7978)
val md_theme_light_inverseOnSurface = Color(0xFFAFFFFB)
val md_theme_light_inverseSurface = Color(0xFF003735)
val md_theme_light_inversePrimary = Color(0xFF4DDAD6)
val md_theme_light_shadow = Color(0xFF000000)
val md_theme_light_surfaceTint = Color(0xFF006A68)

val md_theme_dark_primary = Color(0xFF4DDAD6)
val md_theme_dark_onPrimary = Color(0xFF003736)
val md_theme_dark_primaryContainer = Color(0xFF00504E)
val md_theme_dark_onPrimaryContainer = Color(0xFF6FF7F3)
val md_theme_dark_secondary = Color(0xFFAFC6FF)
val md_theme_dark_onSecondary = Color(0xFF002D6C)
val md_theme_dark_secondaryContainer = Color(0xFF15448F)
val md_theme_dark_onSecondaryContainer = Color(0xFFD9E2FF)
val md_theme_dark_tertiary = Color(0xFF61E05B)
val md_theme_dark_onTertiary = Color(0xFF003A05)
val md_theme_dark_tertiaryContainer = Color(0xFF00530A)
val md_theme_dark_onTertiaryContainer = Color(0xFF7EFE74)
val md_theme_dark_error = Color(0xFFFFB4AB)
val md_theme_dark_errorContainer = Color(0xFF93000A)
val md_theme_dark_onError = Color(0xFF690005)
val md_theme_dark_onErrorContainer = Color(0xFFFFDAD6)
val md_theme_dark_background = Color(0xFF00201F)
val md_theme_dark_onBackground = Color(0xFF6FF7F2)
val md_theme_dark_surface = Color(0xFF00201F)
val md_theme_dark_onSurface = Color(0xFF6FF7F2)
val md_theme_dark_surfaceVariant = Color(0xFF3F4948)
val md_theme_dark_onSurfaceVariant = Color(0xFFBEC9C7)
val md_theme_dark_outline = Color(0xFF889392)
val md_theme_dark_inverseOnSurface = Color(0xFF00201F)
val md_theme_dark_inverseSurface = Color(0xFF6FF7F2)
val md_theme_dark_inversePrimary = Color(0xFF006A68)
val md_theme_dark_shadow = Color(0xFF000000)
val md_theme_dark_surfaceTint = Color(0xFF4DDAD6)


val seed = Color(0xFF93DCD9)


val LowPriorityColor = Color(0xFF00C980)
val MediumPriorityColor = Color(0xFFFFC114)
val HighPriorityColor = Color(0xFFFF4646)
val NonePriorityColor = Color(0xFFC5C5C5)

val BorderColor @Composable get() =
    if (isSystemInDarkTheme()) Color.White.copy(alpha = HALF_ALPHA)
    else Color.Black.copy(alpha = HALF_ALPHA)

val TextColor @Composable get() =
    if (isSystemInDarkTheme()) Color.White.copy(alpha = 0.70f)
    else Color.Black.copy(alpha = HALF_ALPHA)