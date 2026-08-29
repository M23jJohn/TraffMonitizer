package com.example.ui.screens

import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BatteryAlert
import androidx.compose.material.icons.filled.BatteryChargingFull
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.ContentPaste
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.OpenInNew
import androidx.compose.material.icons.filled.PowerSettingsNew
import androidx.compose.material.icons.filled.Router
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.filled.VpnKey
import androidx.compose.material.icons.filled.Wifi
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.AppSettings
import com.example.ui.theme.AccentAmber
import com.example.ui.theme.AccentError
import com.example.ui.theme.BgDark
import com.example.ui.theme.OnPrimaryContainerLavender
import com.example.ui.theme.PrimaryLavender
import com.example.ui.theme.SecondaryCyan
import com.example.ui.theme.SurfaceBorder
import com.example.ui.theme.SurfaceDark
import com.example.ui.theme.SurfaceElevated
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

@Composable
fun SettingsScreen(
  settings: AppSettings,
  onSaveSettings: (AppSettings) -> Unit,
  onClearHistory: () -> Unit,
  modifier: Modifier = Modifier
) {
  val context = LocalContext.current

  var token by remember(settings.token) { mutableStateOf(settings.token) }
  var deviceName by remember(settings.deviceName) { mutableStateOf(settings.deviceName) }
  var isTokenVisible by remember { mutableStateOf(false) }

  var autoStartOnBoot by remember(settings.autoStartOnBoot) { mutableStateOf(settings.autoStartOnBoot) }
  var wifiOnly by remember(settings.wifiOnly) { mutableStateOf(settings.wifiOnly) }
  var chargingOnly by remember(settings.chargingOnly) { mutableStateOf(settings.chargingOnly) }
  var wakeLockEnabled by remember(settings.wakeLockEnabled) { mutableStateOf(settings.wakeLockEnabled) }
  var dailyLimitMb by remember(settings.dailyLimitMb) { mutableStateOf(settings.dailyLimitMb.toString()) }

  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .background(BgDark)
      .padding(horizontal = 16.dp),
    contentPadding = PaddingValues(top = 16.dp, bottom = 32.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    // Header
    item {
      Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = SurfaceDark),
        shape = RoundedCornerShape(20.dp),
        border = CardDefaults.outlinedCardBorder().copy(brush = SolidColor(SurfaceBorder))
      ) {
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .padding(18.dp)
        ) {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
              modifier = Modifier
                .size(38.dp)
                .clip(CircleShape)
                .background(PrimaryLavender.copy(alpha = 0.15f)),
              contentAlignment = Alignment.Center
            ) {
              Icon(Icons.Default.Tune, contentDescription = null, tint = PrimaryLavender, modifier = Modifier.size(20.dp))
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
              Text("Node Configuration", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold), color = TextPrimary)
              Text("TraffMonetizer authentication & policy settings", style = MaterialTheme.typography.bodyMedium.copy(fontSize = 12.sp), color = TextSecondary)
            }
          }
        }
      }
    }

    // Token & Credentials Section
    item {
      Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = SurfaceDark),
        shape = RoundedCornerShape(20.dp),
        border = CardDefaults.outlinedCardBorder().copy(brush = SolidColor(SurfaceBorder))
      ) {
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .padding(18.dp)
        ) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
              Icon(Icons.Default.VpnKey, contentDescription = null, tint = PrimaryLavender, modifier = Modifier.size(18.dp))
              Spacer(modifier = Modifier.width(8.dp))
              Text("Application Token", style = MaterialTheme.typography.titleMedium.copy(fontSize = 15.sp, fontWeight = FontWeight.Bold), color = TextPrimary)
            }

            OutlinedButton(
              onClick = {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://app.traffmonetizer.com"))
                try {
                  context.startActivity(intent)
                } catch (_: Exception) {}
              },
              shape = RoundedCornerShape(10.dp),
              modifier = Modifier.height(32.dp),
              contentPadding = PaddingValues(horizontal = 8.dp, vertical = 2.dp)
            ) {
              Text("Open Dashboard", style = MaterialTheme.typography.labelSmall, color = PrimaryLavender)
              Spacer(modifier = Modifier.width(4.dp))
              Icon(Icons.Default.OpenInNew, contentDescription = null, modifier = Modifier.size(12.dp), tint = PrimaryLavender)
            }
          }

          Spacer(modifier = Modifier.height(10.dp))

          OutlinedTextField(
            value = token,
            onValueChange = {
              token = it
              onSaveSettings(settings.copy(token = it.trim()))
            },
            placeholder = { Text("Paste your real TraffMonetizer application token", color = TextMuted) },
            singleLine = true,
            visualTransformation = if (isTokenVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
              Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = { isTokenVisible = !isTokenVisible }) {
                  Icon(
                    imageVector = if (isTokenVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                    contentDescription = "Toggle Visibility",
                    tint = TextSecondary,
                    modifier = Modifier.size(18.dp)
                  )
                }

                IconButton(
                  onClick = {
                    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                    val clip = clipboard.primaryClip
                    if (clip != null && clip.itemCount > 0) {
                      val text = clip.getItemAt(0).text.toString().trim()
                      token = text
                      onSaveSettings(settings.copy(token = text))
                      Toast.makeText(context, "Token pasted from clipboard!", Toast.LENGTH_SHORT).show()
                    } else {
                      Toast.makeText(context, "Clipboard is empty", Toast.LENGTH_SHORT).show()
                    }
                  }
                ) {
                  Icon(Icons.Default.ContentPaste, contentDescription = "Paste", tint = PrimaryLavender, modifier = Modifier.size(18.dp))
                }
              }
            },
            modifier = Modifier
              .fillMaxWidth()
              .testTag("token_input_field"),
            colors = OutlinedTextFieldDefaults.colors(
              focusedBorderColor = PrimaryLavender,
              unfocusedBorderColor = SurfaceBorder,
              focusedTextColor = TextPrimary,
              unfocusedTextColor = TextPrimary,
              focusedContainerColor = SurfaceElevated,
              unfocusedContainerColor = SurfaceElevated
            ),
            shape = RoundedCornerShape(14.dp)
          )

          Spacer(modifier = Modifier.height(10.dp))

          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
          ) {
            Button(
              onClick = {
                val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clip = clipboard.primaryClip
                if (clip != null && clip.itemCount > 0) {
                  val text = clip.getItemAt(0).text.toString().trim()
                  token = text
                  onSaveSettings(settings.copy(token = text))
                  Toast.makeText(context, "Token saved and activated!", Toast.LENGTH_SHORT).show()
                } else {
                  Toast.makeText(context, "No text in clipboard", Toast.LENGTH_SHORT).show()
                }
              },
              colors = ButtonDefaults.buttonColors(containerColor = PrimaryLavender, contentColor = OnPrimaryContainerLavender),
              shape = RoundedCornerShape(10.dp),
              modifier = Modifier.weight(1f)
            ) {
              Icon(Icons.Default.ContentPaste, contentDescription = null, modifier = Modifier.size(16.dp))
              Spacer(modifier = Modifier.width(6.dp))
              Text("Paste from Clipboard", style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold))
            }

            if (token.isNotEmpty()) {
              Button(
                onClick = {
                  token = ""
                  onSaveSettings(settings.copy(token = ""))
                  Toast.makeText(context, "Token cleared", Toast.LENGTH_SHORT).show()
                },
                colors = ButtonDefaults.buttonColors(containerColor = SurfaceElevated, contentColor = AccentError),
                shape = RoundedCornerShape(10.dp)
              ) {
                Icon(Icons.Default.Clear, contentDescription = "Clear Token", modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text("Clear", style = MaterialTheme.typography.labelSmall)
              }
            }
          }

          Spacer(modifier = Modifier.height(16.dp))

          // Device Alias
          Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.Router, contentDescription = null, tint = PrimaryLavender, modifier = Modifier.size(18.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text("Device Name Alias", style = MaterialTheme.typography.titleMedium.copy(fontSize = 15.sp, fontWeight = FontWeight.Bold), color = TextPrimary)
          }

          Spacer(modifier = Modifier.height(10.dp))

          OutlinedTextField(
            value = deviceName,
            onValueChange = {
              deviceName = it
              onSaveSettings(settings.copy(deviceName = it.trim()))
            },
            placeholder = { Text("e.g. Pixel-Node-01", color = TextMuted) },
            singleLine = true,
            modifier = Modifier
              .fillMaxWidth()
              .testTag("device_name_input_field"),
            colors = OutlinedTextFieldDefaults.colors(
              focusedBorderColor = PrimaryLavender,
              unfocusedBorderColor = SurfaceBorder,
              focusedTextColor = TextPrimary,
              unfocusedTextColor = TextPrimary,
              focusedContainerColor = SurfaceElevated,
              unfocusedContainerColor = SurfaceElevated
            ),
            shape = RoundedCornerShape(14.dp)
          )
        }
      }
    }

    // Power & Network Policy
    item {
      Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = SurfaceDark),
        shape = RoundedCornerShape(20.dp),
        border = CardDefaults.outlinedCardBorder().copy(brush = SolidColor(SurfaceBorder))
      ) {
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .padding(18.dp),
          verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
          Text(
            text = "Bandwidth & Power Policies",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            color = TextPrimary
          )

          // Auto-start on boot
          SettingToggleRow(
            title = "Start on Boot",
            subtitle = "Automatically run node service when Android starts",
            icon = Icons.Default.PowerSettingsNew,
            checked = autoStartOnBoot,
            onCheckedChange = {
              autoStartOnBoot = it
              onSaveSettings(settings.copy(autoStartOnBoot = it))
            }
          )

          // Only on Wi-Fi
          SettingToggleRow(
            title = "Wi-Fi Only",
            subtitle = "Pause sharing when connected to Mobile Data / Cellular",
            icon = Icons.Default.Wifi,
            checked = wifiOnly,
            onCheckedChange = {
              wifiOnly = it
              onSaveSettings(settings.copy(wifiOnly = it))
            }
          )

          // Only when charging
          SettingToggleRow(
            title = "Only When Charging",
            subtitle = "Pause sharing when running on battery to conserve power",
            icon = Icons.Default.BatteryChargingFull,
            checked = chargingOnly,
            onCheckedChange = {
              chargingOnly = it
              onSaveSettings(settings.copy(chargingOnly = it))
            }
          )

          // WakeLock
          SettingToggleRow(
            title = "Keep CPU Awake (WakeLock)",
            subtitle = "Prevent Android from putting background node to deep sleep",
            icon = Icons.Default.Speed,
            checked = wakeLockEnabled,
            onCheckedChange = {
              wakeLockEnabled = it
              onSaveSettings(settings.copy(wakeLockEnabled = it))
            }
          )

          Spacer(modifier = Modifier.height(4.dp))

          // Daily Data Limit
          OutlinedTextField(
            value = dailyLimitMb,
            onValueChange = {
              dailyLimitMb = it
              val limit = it.toIntOrNull() ?: 0
              onSaveSettings(settings.copy(dailyLimitMb = limit))
            },
            label = { Text("Daily Data Limit (MB) - 0 for unlimited") },
            placeholder = { Text("0", color = TextMuted) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
              focusedBorderColor = PrimaryLavender,
              unfocusedBorderColor = SurfaceBorder,
              focusedTextColor = TextPrimary,
              unfocusedTextColor = TextPrimary,
              focusedContainerColor = SurfaceElevated,
              unfocusedContainerColor = SurfaceElevated
            ),
            shape = RoundedCornerShape(14.dp)
          )
        }
      }
    }

    // System Battery Optimization Settings Helper
    item {
      Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = SurfaceDark),
        shape = RoundedCornerShape(20.dp),
        border = CardDefaults.outlinedCardBorder().copy(brush = SolidColor(SurfaceBorder))
      ) {
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .padding(18.dp)
        ) {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.BatteryAlert, contentDescription = null, tint = AccentAmber, modifier = Modifier.size(20.dp))
            Spacer(modifier = Modifier.width(10.dp))
            Text("Android Battery Optimization", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold), color = TextPrimary)
          }

          Spacer(modifier = Modifier.height(8.dp))

          Text(
            text = "For uninterrupted 24/7 background bandwidth sharing, allow TraffMonetizer to run unrestricted in Android battery settings.",
            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 13.sp),
            color = TextSecondary
          )

          Spacer(modifier = Modifier.height(12.dp))

          Button(
            onClick = {
              if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                try {
                  val intent = Intent(Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS)
                  context.startActivity(intent)
                } catch (_: Exception) {
                  val intent = Intent(Settings.ACTION_SETTINGS)
                  context.startActivity(intent)
                }
              }
            },
            colors = ButtonDefaults.buttonColors(containerColor = SurfaceElevated, contentColor = PrimaryLavender),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth()
          ) {
            Text("Open Battery Optimization Settings", style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold))
          }
        }
      }
    }

    // Reset / Clear Data
    item {
      Button(
        onClick = {
          onClearHistory()
          Toast.makeText(context, "Session history cleared", Toast.LENGTH_SHORT).show()
        },
        colors = ButtonDefaults.buttonColors(containerColor = SurfaceDark, contentColor = AccentError),
        shape = RoundedCornerShape(16.dp),
        border = CardDefaults.outlinedCardBorder().copy(brush = SolidColor(AccentError.copy(alpha = 0.3f))),
        modifier = Modifier.fillMaxWidth()
      ) {
        Icon(Icons.Default.Delete, contentDescription = null, modifier = Modifier.size(16.dp))
        Spacer(modifier = Modifier.width(8.dp))
        Text("Clear Session History & Database")
      }
    }
  }
}

@Composable
private fun SettingToggleRow(
  title: String,
  subtitle: String,
  icon: androidx.compose.ui.graphics.vector.ImageVector,
  checked: Boolean,
  onCheckedChange: (Boolean) -> Unit
) {
  Row(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically
  ) {
    Row(
      modifier = Modifier.weight(1f),
      verticalAlignment = Alignment.CenterVertically
    ) {
      Box(
        modifier = Modifier
          .size(36.dp)
          .clip(RoundedCornerShape(10.dp))
          .background(SurfaceElevated),
        contentAlignment = Alignment.Center
      ) {
        Icon(icon, contentDescription = null, tint = if (checked) PrimaryLavender else TextMuted, modifier = Modifier.size(18.dp))
      }
      Spacer(modifier = Modifier.width(12.dp))
      Column {
        Text(title, style = MaterialTheme.typography.titleMedium.copy(fontSize = 14.sp, fontWeight = FontWeight.Bold), color = TextPrimary)
        Text(subtitle, style = MaterialTheme.typography.bodyMedium.copy(fontSize = 11.sp), color = TextSecondary)
      }
    }

    Switch(
      checked = checked,
      onCheckedChange = onCheckedChange,
      colors = SwitchDefaults.colors(
        checkedThumbColor = OnPrimaryContainerLavender,
        checkedTrackColor = PrimaryLavender,
        uncheckedThumbColor = TextMuted,
        uncheckedTrackColor = SurfaceElevated
      )
    )
  }
}

