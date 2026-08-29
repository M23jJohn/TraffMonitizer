package com.example.ui.screens

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.Layers
import androidx.compose.material.icons.filled.OpenInNew
import androidx.compose.material.icons.filled.Terminal
import androidx.compose.material.icons.filled.VpnKey
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.AppSettings
import com.example.ui.theme.AccentAmber
import com.example.ui.theme.AccentOnline
import com.example.ui.theme.BgDark
import com.example.ui.theme.OnPrimaryContainerLavender
import com.example.ui.theme.PrimaryLavender
import com.example.ui.theme.SecondaryCyan
import com.example.ui.theme.SurfaceBorder
import com.example.ui.theme.SurfaceBorderSubtle
import com.example.ui.theme.SurfaceDark
import com.example.ui.theme.SurfaceElevated
import com.example.ui.theme.TerminalBg
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

@Composable
fun DockerGenScreen(
  settings: AppSettings,
  modifier: Modifier = Modifier
) {
  val context = LocalContext.current
  var containerName by remember { mutableStateOf("tm") }
  var customDeviceName by remember { mutableStateOf(settings.deviceName) }
  var restartPolicy by remember { mutableStateOf("always") }
  var selectedArch by remember { mutableStateOf("linux/amd64") }
  var selectedTab by remember { mutableIntStateOf(0) }

  val hasToken = settings.token.isNotBlank()
  val token = if (hasToken) settings.token else "YOUR_REAL_APPLICATION_TOKEN"

  val archFlag = if (selectedArch == "auto") "" else "--platform $selectedArch "

  val dockerRunCmd = "docker run -d --name $containerName ${archFlag}--restart $restartPolicy traffmonetizer/cli_v2 start accept --token $token --device-name $customDeviceName"

  val dockerCompose = """
version: '3.8'

services:
  traffmonetizer:
    image: traffmonetizer/cli_v2:latest
    container_name: $containerName
    platform: ${if (selectedArch == "auto") "linux/amd64" else selectedArch}
    restart: $restartPolicy
    command: start accept --token $token --device-name $customDeviceName
    logging:
      driver: "json-file"
      options:
        max-size: "10m"
        max-file: "3"
""".trimIndent()

  val termuxAndroidCmd = """
# Run TraffMonetizer Container on Android via Termux / PRoot
pkg update -y && pkg install -y proot-distro
proot-distro install debian
proot-distro login debian -- bash -c "
  apt update && apt install -y curl
  # Launch TraffMonetizer CLI v2 node directly
  docker run -d --name $containerName --restart $restartPolicy traffmonetizer/cli_v2 start accept --token $token --device-name $customDeviceName
"
""".trimIndent()

  val systemdUnit = """
[Unit]
Description=TraffMonetizer CLI v2 Node Service
After=docker.service
Requires=docker.service

[Service]
TimeoutStartSec=0
Restart=always
ExecStartPre=-/usr/bin/docker stop $containerName
ExecStartPre=-/usr/bin/docker rm $containerName
ExecStart=/usr/bin/docker run --name $containerName --rm ${archFlag}traffmonetizer/cli_v2 start accept --token $token --device-name $customDeviceName

[Install]
WantedBy=multi-user.target
""".trimIndent()

  val powershellCmd = """
# Run on Windows PowerShell / Docker Desktop
docker run -d --name $containerName --restart $restartPolicy traffmonetizer/cli_v2 start accept --token "$token" --device-name "$customDeviceName"
""".trimIndent()

  val activeSnippet = when (selectedTab) {
    0 -> dockerRunCmd
    1 -> dockerCompose
    2 -> termuxAndroidCmd
    3 -> systemdUnit
    else -> powershellCmd
  }

  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .background(BgDark)
      .padding(horizontal = 16.dp),
    contentPadding = PaddingValues(top = 12.dp, bottom = 32.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    // Quick Hub & Dashboard Access Bar
    item {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        Button(
          onClick = {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://hub.docker.com/r/traffmonetizer/cli_v2"))
            try { context.startActivity(intent) } catch (_: Exception) {}
          },
          colors = ButtonDefaults.buttonColors(containerColor = SurfaceElevated, contentColor = PrimaryLavender),
          shape = RoundedCornerShape(12.dp),
          modifier = Modifier.weight(1f)
        ) {
          Icon(Icons.Default.OpenInNew, contentDescription = null, modifier = Modifier.size(14.dp))
          Spacer(modifier = Modifier.width(6.dp))
          Text("Docker Hub Image", style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold))
        }

        Button(
          onClick = {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://app.traffmonetizer.com"))
            try { context.startActivity(intent) } catch (_: Exception) {}
          },
          colors = ButtonDefaults.buttonColors(containerColor = SurfaceElevated, contentColor = SecondaryCyan),
          shape = RoundedCornerShape(12.dp),
          modifier = Modifier.weight(1f)
        ) {
          Icon(Icons.Default.OpenInNew, contentDescription = null, modifier = Modifier.size(14.dp))
          Spacer(modifier = Modifier.width(6.dp))
          Text("TraffMonetizer Dashboard", style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold))
        }
      }
    }

    // Token Status Card
    item {
      Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
          containerColor = if (hasToken) Color(0xFF14241B) else Color(0xFF261D15)
        ),
        shape = RoundedCornerShape(16.dp),
        border = CardDefaults.outlinedCardBorder().copy(
          brush = SolidColor(if (hasToken) AccentOnline.copy(alpha = 0.5f) else AccentAmber.copy(alpha = 0.5f))
        )
      ) {
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(14.dp),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.SpaceBetween
        ) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(1f)
          ) {
            Icon(
              imageVector = if (hasToken) Icons.Default.CheckCircle else Icons.Default.Warning,
              contentDescription = null,
              tint = if (hasToken) AccentOnline else AccentAmber,
              modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column {
              Text(
                text = if (hasToken) "Real Token Linked & Injected" else "No Token Saved Yet",
                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                color = if (hasToken) AccentOnline else AccentAmber
              )
              Text(
                text = if (hasToken)
                  "Token: ${settings.token.take(6)}...${settings.token.takeLast(4)}"
                else
                  "Commands currently use placeholder token",
                style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp),
                color = TextSecondary
              )
            }
          }

          if (!hasToken) {
            OutlinedButton(
              onClick = {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://app.traffmonetizer.com"))
                try { context.startActivity(intent) } catch (_: Exception) {}
              },
              shape = RoundedCornerShape(8.dp),
              contentPadding = PaddingValues(horizontal = 8.dp, vertical = 2.dp),
              modifier = Modifier.height(30.dp)
            ) {
              Text("Get Key", style = MaterialTheme.typography.labelSmall, color = AccentAmber)
            }
          }
        }
      }
    }

    // Configurator Card
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
              Icon(Icons.Default.Layers, contentDescription = null, tint = PrimaryLavender, modifier = Modifier.size(20.dp))
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
              Text("traffmonetizer/cli_v2 Generator", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold), color = TextPrimary)
              Text("Docker container runner configuration", style = MaterialTheme.typography.bodyMedium.copy(fontSize = 12.sp), color = TextSecondary)
            }
          }

          Spacer(modifier = Modifier.height(16.dp))

          // Target Architecture Selector
          Text("Target Architecture", style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold), color = TextSecondary)
          Spacer(modifier = Modifier.height(6.dp))
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
          ) {
            listOf("linux/amd64" to "x86_64", "linux/arm64" to "ARM64", "linux/arm/v7" to "ARMv7").forEach { (arch, label) ->
              FilterChip(
                selected = selectedArch == arch,
                onClick = { selectedArch = arch },
                label = { Text(label, style = MaterialTheme.typography.labelSmall) },
                colors = FilterChipDefaults.filterChipColors(
                  selectedContainerColor = PrimaryLavender,
                  selectedLabelColor = OnPrimaryContainerLavender,
                  containerColor = SurfaceElevated,
                  labelColor = TextSecondary
                )
              )
            }
          }

          Spacer(modifier = Modifier.height(12.dp))

          OutlinedTextField(
            value = customDeviceName,
            onValueChange = { customDeviceName = it },
            label = { Text("Device Name (--device-name)") },
            placeholder = { Text("e.g. android-vps-01", color = TextMuted) },
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

          Spacer(modifier = Modifier.height(10.dp))

          OutlinedTextField(
            value = containerName,
            onValueChange = { containerName = it },
            label = { Text("Container Name (--name)") },
            placeholder = { Text("tm", color = TextMuted) },
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

    // Generated Code Card with Tabs
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
          ScrollableTabRow(
            selectedTabIndex = selectedTab,
            containerColor = SurfaceElevated,
            contentColor = PrimaryLavender,
            edgePadding = 0.dp,
            indicator = { tabPositions ->
              TabRowDefaults.SecondaryIndicator(
                Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
                color = PrimaryLavender,
                height = 2.dp
              )
            },
            modifier = Modifier.clip(RoundedCornerShape(12.dp))
          ) {
            Tab(
              selected = selectedTab == 0,
              onClick = { selectedTab = 0 },
              text = { Text("Docker CLI", style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold)) },
              selectedContentColor = PrimaryLavender,
              unselectedContentColor = TextSecondary
            )
            Tab(
              selected = selectedTab == 1,
              onClick = { selectedTab = 1 },
              text = { Text("Compose", style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold)) },
              selectedContentColor = PrimaryLavender,
              unselectedContentColor = TextSecondary
            )
            Tab(
              selected = selectedTab == 2,
              onClick = { selectedTab = 2 },
              text = { Text("Termux Android", style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold)) },
              selectedContentColor = PrimaryLavender,
              unselectedContentColor = TextSecondary
            )
            Tab(
              selected = selectedTab == 3,
              onClick = { selectedTab = 3 },
              text = { Text("Systemd", style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold)) },
              selectedContentColor = PrimaryLavender,
              unselectedContentColor = TextSecondary
            )
            Tab(
              selected = selectedTab == 4,
              onClick = { selectedTab = 4 },
              text = { Text("PowerShell", style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold)) },
              selectedContentColor = PrimaryLavender,
              unselectedContentColor = TextSecondary
            )
          }

          Spacer(modifier = Modifier.height(14.dp))

          Box(
            modifier = Modifier
              .fillMaxWidth()
              .clip(RoundedCornerShape(14.dp))
              .background(TerminalBg)
              .border(1.dp, SurfaceBorder, RoundedCornerShape(14.dp))
              .padding(14.dp)
          ) {
            Text(
              text = activeSnippet,
              style = MaterialTheme.typography.labelSmall.copy(
                fontFamily = FontFamily.Monospace,
                fontSize = 12.sp,
                color = SecondaryCyan,
                lineHeight = 18.sp
              )
            )
          }

          Spacer(modifier = Modifier.height(14.dp))

          Button(
            onClick = {
              val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
              clipboard.setPrimaryClip(ClipData.newPlainText("TraffMonetizer Script", activeSnippet))
              Toast.makeText(context, "Snippet copied to clipboard!", Toast.LENGTH_SHORT).show()
            },
            colors = ButtonDefaults.buttonColors(
              containerColor = PrimaryLavender,
              contentColor = OnPrimaryContainerLavender
            ),
            shape = RoundedCornerShape(14.dp),
            modifier = Modifier
              .fillMaxWidth()
              .testTag("copy_snippet_button")
          ) {
            Icon(Icons.Default.ContentCopy, contentDescription = null, modifier = Modifier.size(18.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text("Copy Container Runner Command", style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold))
          }
        }
      }
    }

    // Reference & Documentation Accordion
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
            Icon(Icons.Default.HelpOutline, contentDescription = null, tint = SecondaryCyan, modifier = Modifier.size(20.dp))
            Spacer(modifier = Modifier.width(10.dp))
            Text("Official Docker Hub & CLI Reference", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold), color = TextPrimary)
          }

          Spacer(modifier = Modifier.height(12.dp))

          Text(
            text = "• Container Image: traffmonetizer/cli_v2\n• Hub URL: https://hub.docker.com/r/traffmonetizer/cli_v2\n• Supported Platforms: linux/amd64, linux/arm64, linux/arm/v7\n• CLI Action: start accept --token <TOKEN> --device-name <NAME>\n• Container Logs: docker logs -f tm\n• Container Status: docker ps -a",
            style = MaterialTheme.typography.bodyMedium.copy(
              fontSize = 13.sp,
              lineHeight = 22.sp
            ),
            color = TextSecondary
          )
        }
      }
    }
  }
}

