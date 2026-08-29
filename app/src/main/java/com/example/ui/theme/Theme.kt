package com.example.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
  primary = PrimaryLavender,
  onPrimary = Color(0xFF381E72),
  primaryContainer = PrimaryContainerLavender,
  onPrimaryContainer = OnPrimaryContainerLavender,
  secondary = SecondaryCyan,
  onSecondary = Color(0xFF00363D),
  secondaryContainer = Color(0xFF4A4458),
  onSecondaryContainer = Color(0xFFEADDFF),
  tertiary = TertiaryViolet,
  onTertiary = Color(0xFF332D41),
  background = BgDark,
  onBackground = TextPrimary,
  surface = SurfaceDark,
  onSurface = TextPrimary,
  surfaceVariant = SurfaceElevated,
  onSurfaceVariant = TextSecondary,
  outline = SurfaceBorder,
  outlineVariant = SurfaceBorderSubtle,
  error = AccentError,
  onError = Color(0xFF601410)
)

@Composable
fun MyApplicationTheme(
  content: @Composable () -> Unit
) {
  MaterialTheme(
    colorScheme = DarkColorScheme,
    typography = Typography,
    content = content
  )
}

