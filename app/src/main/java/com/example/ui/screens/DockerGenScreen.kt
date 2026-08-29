package com.example.ui.screens

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.Layers
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
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
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.AppSettings
import com.example.ui.theme.BgDark
import com.example.ui.theme.OnPrimaryContainerLavender
import com.example.ui.theme.PrimaryLavender
import com.example.ui.theme.SecondaryCyan
import com.example.ui.theme.SurfaceBorder
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
  var selectedTab by remember { mutableIntStateOf(0) }

  val token = if (settings.token.isNotBlank()) settings.token else "YOUR_APPLICATION_TOKEN"

  val dockerRunCmd = "docker run -d --name $containerName --restart $restartPolicy traffmonetizer/cli_v2 start accept --token $token --device-name $customDeviceName"

  val dockerCompose = """
version: '3.8'

services:
  traffmonetizer:
    image: traffmonetizer/cli_v2:latest
    container_name: $containerName
    restart: $restartPolicy
    command: start accept --token $token --device-name $customDeviceName
    logging:
      driver: "json-file"
      options:
        max-size: "10m"
        max-file: "3"
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
ExecStart=/usr/bin/docker run --name $containerName --rm traffmonetizer/cli_v2 start accept --token $token --device-name $customDeviceName

[Install]
WantedBy=multi-user.target
""".trimIndent()

  val powershellCmd = """
# Run on Windows PowerShell
docker run -d --name $containerName --restart $restartPolicy traffmonetizer/cli_v2 start accept --token "$token" --device-name "$customDeviceName"
""".trimIndent()

  val activeSnippet = when (selectedTab) {
    0 -> dockerRunCmd
    1 -> dockerCompose
    2 -> systemdUnit
    else -> powershellCmd
  }

  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .background(BgDark)
      .padding(horizontal = 16.dp),
    contentPadding = PaddingValues(top = 16.dp, bottom = 32.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {
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
              Text("Docker Hub official container configurator", style = MaterialTheme.typography.bodyMedium.copy(fontSize = 12.sp), color = TextSecondary)
            }
          }

          Spacer(modifier = Modifier.height(16.dp))

          OutlinedTextField(
            value = customDeviceName,
            onValueChange = { customDeviceName = it },
            label = { Text("Device Name (--device-name)") },
            placeholder = { Text("e.g. vps-us-east-1", color = TextMuted) },
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
          TabRow(
            selectedTabIndex = selectedTab,
            containerColor = SurfaceElevated,
            contentColor = PrimaryLavender,
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
              text = { Text("Systemd", style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold)) },
              selectedContentColor = PrimaryLavender,
              unselectedContentColor = TextSecondary
            )
            Tab(
              selected = selectedTab == 3,
              onClick = { selectedTab = 3 },
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
            Text("Copy Snippet", style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold))
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
            Text("CLI v2 Docker Reference", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold), color = TextPrimary)
          }

          Spacer(modifier = Modifier.height(12.dp))

          Text(
            text = "• Container Image: traffmonetizer/cli_v2\n• Supported Architectures: linux/amd64, linux/arm64, linux/arm/v7\n• CLI Syntax: start accept --token <TOKEN> --device-name <NAME>\n• View Live Logs: docker logs -f tm\n• Check Container Status: docker ps -a",
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

