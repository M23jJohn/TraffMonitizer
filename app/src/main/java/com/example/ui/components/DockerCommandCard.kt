package com.example.ui.components

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import com.example.ui.theme.OnPrimaryContainerLavender
import com.example.ui.theme.PrimaryLavender
import com.example.ui.theme.SecondaryCyan
import com.example.ui.theme.SurfaceBorder
import com.example.ui.theme.SurfaceDark
import com.example.ui.theme.SurfaceElevated
import com.example.ui.theme.TerminalBg
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

@Composable
fun DockerCommandCard(
  token: String,
  deviceName: String,
  modifier: Modifier = Modifier
) {
  val context = LocalContext.current
  var selectedTab by remember { mutableIntStateOf(0) }

  val effectiveToken = if (token.isNotBlank()) token else "YOUR_APP_TOKEN"
  val effectiveDevice = if (deviceName.isNotBlank()) deviceName else "vps-node-01"

  val dockerRunCommand = "docker run -d --name tm --restart always traffmonetizer/cli_v2 start accept --token $effectiveToken --device-name $effectiveDevice"

  val dockerComposeYaml = """
version: '3'
services:
  traffmonetizer:
    image: traffmonetizer/cli_v2
    container_name: tm
    restart: always
    command: start accept --token $effectiveToken --device-name $effectiveDevice
""".trimIndent()

  val powershellCmd = "docker run -d --name tm --restart always traffmonetizer/cli_v2 start accept --token \$env:TM_TOKEN --device-name $effectiveDevice"

  val currentContent = when (selectedTab) {
    0 -> dockerRunCommand
    1 -> dockerComposeYaml
    else -> powershellCmd
  }

  Card(
    modifier = modifier.fillMaxWidth(),
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
          Box(
            modifier = Modifier
              .size(34.dp)
              .clip(RoundedCornerShape(10.dp))
              .background(PrimaryLavender.copy(alpha = 0.15f)),
            contentAlignment = Alignment.Center
          ) {
            Icon(
              imageVector = Icons.Default.Code,
              contentDescription = "Docker CLI",
              tint = PrimaryLavender,
              modifier = Modifier.size(18.dp)
            )
          }
          Spacer(modifier = Modifier.width(10.dp))
          Column {
            Text(
              text = "Docker CLI Exporter",
              style = MaterialTheme.typography.titleMedium.copy(fontSize = 15.sp, fontWeight = FontWeight.Bold),
              color = TextPrimary
            )
            Text(
              text = "traffmonetizer/cli_v2 runner syntax",
              style = MaterialTheme.typography.bodyMedium.copy(fontSize = 12.sp),
              color = TextSecondary
            )
          }
        }
      }

      Spacer(modifier = Modifier.height(14.dp))

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
          text = { Text("docker run", style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold)) },
          selectedContentColor = PrimaryLavender,
          unselectedContentColor = TextSecondary
        )
        Tab(
          selected = selectedTab == 1,
          onClick = { selectedTab = 1 },
          text = { Text("compose.yml", style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold)) },
          selectedContentColor = PrimaryLavender,
          unselectedContentColor = TextSecondary
        )
        Tab(
          selected = selectedTab == 2,
          onClick = { selectedTab = 2 },
          text = { Text("PowerShell", style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold)) },
          selectedContentColor = PrimaryLavender,
          unselectedContentColor = TextSecondary
        )
      }

      Spacer(modifier = Modifier.height(12.dp))

      Box(
        modifier = Modifier
          .fillMaxWidth()
          .clip(RoundedCornerShape(14.dp))
          .background(TerminalBg)
          .border(1.dp, SurfaceBorder, RoundedCornerShape(14.dp))
          .padding(14.dp)
      ) {
        Text(
          text = currentContent,
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
          clipboard.setPrimaryClip(ClipData.newPlainText("Docker Command", currentContent))
          Toast.makeText(context, "Command copied to clipboard!", Toast.LENGTH_SHORT).show()
        },
        modifier = Modifier
          .fillMaxWidth()
          .testTag("copy_docker_command_button"),
        colors = ButtonDefaults.buttonColors(
          containerColor = PrimaryLavender,
          contentColor = OnPrimaryContainerLavender
        ),
        shape = RoundedCornerShape(14.dp)
      ) {
        Icon(
          imageVector = Icons.Default.ContentCopy,
          contentDescription = "Copy",
          modifier = Modifier.size(18.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
          text = "Copy Docker Run Command",
          style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold)
        )
      }
    }
  }
}

