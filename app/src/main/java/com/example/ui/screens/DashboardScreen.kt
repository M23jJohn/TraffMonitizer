package com.example.ui.screens

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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AvTimer
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Devices
import androidx.compose.material.icons.filled.NetworkCheck
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Router
import androidx.compose.material.icons.filled.Sensors
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.VpnKey
import androidx.compose.material.icons.filled.Wifi
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.AppSettings
import com.example.data.model.LogEntry
import com.example.data.model.NetworkInfo
import com.example.data.model.NodeStatus
import com.example.data.model.TrafficStats
import com.example.service.TraffMonetizerEngine
import com.example.ui.components.DockerCommandCard
import com.example.ui.components.MasterNodePulseButton
import com.example.ui.components.SpeedGaugeCard
import com.example.ui.components.TerminalLogView
import com.example.ui.theme.AccentAmber
import com.example.ui.theme.AccentOnline
import com.example.ui.theme.AccentWarning
import com.example.ui.theme.BgDark
import com.example.ui.theme.OnPrimaryContainerLavender
import com.example.ui.theme.PrimaryContainerLavender
import com.example.ui.theme.PrimaryLavender
import com.example.ui.theme.SecondaryCyan
import com.example.ui.theme.SurfaceBorder
import com.example.ui.theme.SurfaceBorderSubtle
import com.example.ui.theme.SurfaceDark
import com.example.ui.theme.SurfaceElevated
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import java.util.Locale

@Composable
fun DashboardScreen(
  status: NodeStatus,
  stats: TrafficStats,
  networkInfo: NetworkInfo,
  settings: AppSettings,
  logs: List<LogEntry>,
  onToggleNode: () -> Unit,
  onRefreshNetwork: () -> Unit,
  onSetDemoToken: () -> Unit,
  onNavigateToSettings: () -> Unit,
  onNavigateToTerminal: () -> Unit,
  onClearLogs: () -> Unit,
  modifier: Modifier = Modifier
) {
  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .background(BgDark)
      .padding(horizontal = 16.dp),
    contentPadding = PaddingValues(top = 12.dp, bottom = 32.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    // Elegant Header
    item {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Column {
          Text(
            text = "TRAFFMONETIZER",
            style = MaterialTheme.typography.labelSmall.copy(
              letterSpacing = 1.5.sp,
              fontWeight = FontWeight.SemiBold,
              fontSize = 11.sp
            ),
            color = TextSecondary
          )
          Text(
            text = "Dashboard",
            style = MaterialTheme.typography.headlineSmall.copy(
              fontWeight = FontWeight.Bold,
              fontSize = 22.sp
            ),
            color = TextPrimary
          )
        }

        Box(
          modifier = Modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(SurfaceElevated)
            .border(1.dp, SurfaceBorderSubtle, CircleShape),
          contentAlignment = Alignment.Center
        ) {
          Icon(
            imageVector = Icons.Default.AccountCircle,
            contentDescription = "Account",
            tint = PrimaryLavender,
            modifier = Modifier.size(24.dp)
          )
        }
      }
    }

    // Elegant Hero Balance Card
    item {
      HeroBalanceCard(
        status = status,
        estimatedEarnings = stats.estimatedEarningsUsd
      )
    }

    // Top Network Status & IP Badge
    item {
      NetworkStatusBar(
        networkInfo = networkInfo,
        deviceName = settings.deviceName,
        onRefresh = onRefreshNetwork
      )
    }

    // Token Warning Alert if not set
    if (settings.token.isBlank()) {
      item {
        NoTokenWarningCard(
          onSetDemoToken = onSetDemoToken,
          onGoSettings = onNavigateToSettings
        )
      }
    }

    // Master Pulse Toggle Button
    item {
      Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = SurfaceDark),
        shape = RoundedCornerShape(28.dp),
        border = CardDefaults.outlinedCardBorder().copy(brush = SolidColor(SurfaceBorder))
      ) {
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp, horizontal = 16.dp),
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          MasterNodePulseButton(
            status = status,
            onToggleClick = onToggleNode
          )

          Spacer(modifier = Modifier.height(14.dp))

          // Device alias indicator
          Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
              .clip(RoundedCornerShape(20.dp))
              .background(SurfaceElevated)
              .border(1.dp, SurfaceBorder, RoundedCornerShape(20.dp))
              .padding(horizontal = 14.dp, vertical = 6.dp)
          ) {
            Icon(
              imageVector = Icons.Default.Router,
              contentDescription = null,
              tint = PrimaryLavender,
              modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
              text = "Node: ${settings.deviceName}",
              style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.SemiBold),
              color = TextPrimary
            )
          }
        }
      }
    }

    // Live Speed Dual Gauges
    item {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
      ) {
        SpeedGaugeCard(
          title = "Download",
          bps = stats.currentDownloadBps,
          isUpload = false,
          modifier = Modifier.weight(1f)
        )
        SpeedGaugeCard(
          title = "Upload",
          bps = stats.currentUploadBps,
          isUpload = true,
          modifier = Modifier.weight(1f)
        )
      }
    }

    // 4-Card Performance Stats Grid (Matching Elegant Dark 2x2 grid layout)
    item {
      Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
          ElegantStatCard(
            title = "Traffic Shared Today",
            value = TraffMonetizerEngine.formatBytes(stats.todayBytes),
            icon = Icons.Default.Sensors,
            modifier = Modifier.weight(1f)
          )
          val totalSpeedMbps = ((stats.currentDownloadBps + stats.currentUploadBps) * 8.0) / (1024.0 * 1024.0)
          ElegantStatCard(
            title = "Current Throughput",
            value = String.format(Locale.US, "%.1f Mbps", totalSpeedMbps),
            icon = Icons.Default.Speed,
            modifier = Modifier.weight(1f)
          )
        }

        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
          ElegantStatCard(
            title = "Active Relays",
            value = "${stats.totalRequestsServed}",
            icon = Icons.Default.Devices,
            modifier = Modifier.weight(1f)
          )
          ElegantStatCard(
            title = "Uptime Duration",
            value = TraffMonetizerEngine.formatDuration(stats.sessionDurationSeconds),
            icon = Icons.Default.AvTimer,
            modifier = Modifier.weight(1f)
          )
        }
      }
    }

    // CLI Status & Mini Live Terminal Preview
    item {
      Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = SurfaceElevated.copy(alpha = 0.5f)),
        shape = RoundedCornerShape(20.dp),
        border = CardDefaults.outlinedCardBorder().copy(brush = SolidColor(SurfaceBorder))
      ) {
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Column {
            Text(
              text = "CLI STATUS",
              style = MaterialTheme.typography.labelSmall.copy(
                fontSize = 10.sp,
                letterSpacing = 1.2.sp,
                fontWeight = FontWeight.Bold
              ),
              color = TextSecondary
            )
            Text(
              text = if (status == NodeStatus.ONLINE) "v2.0.4 - Container Active" else "v2.0.4 - Standby",
              style = MaterialTheme.typography.bodyMedium.copy(
                fontFamily = FontFamily.Monospace,
                fontSize = 13.sp
              ),
              color = TextPrimary
            )
          }

          Button(
            onClick = onToggleNode,
            colors = ButtonDefaults.buttonColors(
              containerColor = PrimaryLavender,
              contentColor = OnPrimaryContainerLavender
            ),
            shape = RoundedCornerShape(20.dp),
            contentPadding = PaddingValues(horizontal = 14.dp, vertical = 6.dp)
          ) {
            Text(
              text = if (status == NodeStatus.ONLINE) "RESTART" else "START",
              style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold)
            )
          }
        }
      }
    }

    // Live Container Logs Accordion
    item {
      Column {
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp, vertical = 4.dp),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Text(
            text = "Live Container Logs",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
            color = TextPrimary
          )
          TextButton(onClick = onNavigateToTerminal) {
            Text(
              text = "Full Console",
              color = PrimaryLavender,
              style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold)
            )
            Icon(
              imageVector = Icons.Default.ChevronRight,
              contentDescription = null,
              tint = PrimaryLavender,
              modifier = Modifier.size(16.dp)
            )
          }
        }

        TerminalLogView(
          logs = logs.takeLast(50),
          onClearLogs = onClearLogs,
          showControls = false,
          modifier = Modifier
            .fillMaxWidth()
            .height(190.dp)
        )
      }
    }

    // Quick Docker runner card
    item {
      DockerCommandCard(
        token = settings.token,
        deviceName = settings.deviceName
      )
    }
  }
}

@Composable
private fun HeroBalanceCard(
  status: NodeStatus,
  estimatedEarnings: Double
) {
  Card(
    modifier = Modifier.fillMaxWidth(),
    colors = CardDefaults.cardColors(containerColor = PrimaryContainerLavender),
    shape = RoundedCornerShape(28.dp),
    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
  ) {
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .padding(24.dp)
    ) {
      // Decorative translucent bubble
      Box(
        modifier = Modifier
          .size(96.dp)
          .align(Alignment.TopEnd)
          .offset(x = 16.dp, y = (-16).dp)
          .clip(CircleShape)
          .background(PrimaryLavender.copy(alpha = 0.35f))
      )

      Column(modifier = Modifier.fillMaxWidth()) {
        Text(
          text = "Current Balance",
          style = MaterialTheme.typography.bodyMedium.copy(
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp
          ),
          color = OnPrimaryContainerLavender.copy(alpha = 0.8f)
        )

        Spacer(modifier = Modifier.height(4.dp))

        Row(
          verticalAlignment = Alignment.Bottom,
          horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
          Text(
            text = "$${String.format(Locale.US, "%.2f", estimatedEarnings)}",
            style = MaterialTheme.typography.displaySmall.copy(
              fontWeight = FontWeight.Bold,
              letterSpacing = (-0.5).sp
            ),
            color = OnPrimaryContainerLavender
          )
          Text(
            text = "USD",
            style = MaterialTheme.typography.labelMedium.copy(
              fontWeight = FontWeight.Bold,
              fontSize = 12.sp
            ),
            color = OnPrimaryContainerLavender.copy(alpha = 0.7f),
            modifier = Modifier.padding(bottom = 6.dp)
          )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          Box(
            modifier = Modifier
              .size(8.dp)
              .clip(CircleShape)
              .background(if (status == NodeStatus.ONLINE) AccentOnline else AccentWarning)
          )
          Text(
            text = if (status == NodeStatus.ONLINE) "Service Running: traffmonetizer-cli-v2" else "Service ${status.name.lowercase()}",
            style = MaterialTheme.typography.labelSmall.copy(
              fontWeight = FontWeight.Medium,
              fontSize = 12.sp
            ),
            color = OnPrimaryContainerLavender
          )
        }
      }
    }
  }
}

@Composable
private fun ElegantStatCard(
  title: String,
  value: String,
  icon: ImageVector,
  modifier: Modifier = Modifier
) {
  Card(
    modifier = modifier,
    colors = CardDefaults.cardColors(containerColor = SurfaceDark),
    shape = RoundedCornerShape(24.dp),
    border = CardDefaults.outlinedCardBorder().copy(brush = SolidColor(SurfaceBorder))
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
      verticalArrangement = Arrangement.SpaceBetween
    ) {
      Icon(
        imageVector = icon,
        contentDescription = null,
        tint = PrimaryLavender,
        modifier = Modifier.size(24.dp)
      )

      Spacer(modifier = Modifier.height(14.dp))

      Column {
        Text(
          text = value,
          style = MaterialTheme.typography.titleLarge.copy(
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp
          ),
          color = TextPrimary
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
          text = title,
          style = MaterialTheme.typography.bodySmall.copy(fontSize = 12.sp),
          color = TextSecondary
        )
      }
    }
  }
}

@Composable
private fun NetworkStatusBar(
  networkInfo: NetworkInfo,
  deviceName: String,
  onRefresh: () -> Unit
) {
  Card(
    modifier = Modifier.fillMaxWidth(),
    colors = CardDefaults.cardColors(containerColor = SurfaceDark),
    shape = RoundedCornerShape(20.dp),
    border = CardDefaults.outlinedCardBorder().copy(brush = SolidColor(SurfaceBorder))
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 12.dp),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
          modifier = Modifier
            .size(36.dp)
            .clip(CircleShape)
            .background(SurfaceElevated),
          contentAlignment = Alignment.Center
        ) {
          Icon(
            imageVector = if (networkInfo.isWifi) Icons.Default.Wifi else Icons.Default.NetworkCheck,
            contentDescription = null,
            tint = PrimaryLavender,
            modifier = Modifier.size(20.dp)
          )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
              text = networkInfo.ipAddress,
              style = MaterialTheme.typography.titleMedium.copy(
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
              ),
              color = TextPrimary
            )
            Spacer(modifier = Modifier.width(6.dp))
            Box(
              modifier = Modifier
                .clip(RoundedCornerShape(6.dp))
                .background(PrimaryLavender.copy(alpha = 0.15f))
                .padding(horizontal = 6.dp, vertical = 2.dp)
            ) {
              Text(
                text = "${networkInfo.latencyMs}ms",
                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                color = PrimaryLavender
              )
            }
          }
          Text(
            text = "${networkInfo.networkType} • ${networkInfo.isp}",
            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 12.sp),
            color = TextSecondary
          )
        }
      }

      IconButton(
        onClick = onRefresh,
        modifier = Modifier.testTag("refresh_network_button")
      ) {
        Icon(
          imageVector = Icons.Default.Refresh,
          contentDescription = "Refresh Network Info",
          tint = TextSecondary,
          modifier = Modifier.size(20.dp)
        )
      }
    }
  }
}

@Composable
private fun NoTokenWarningCard(
  onSetDemoToken: () -> Unit,
  onGoSettings: () -> Unit
) {
  Card(
    modifier = Modifier
      .fillMaxWidth()
      .testTag("no_token_warning_card"),
    colors = CardDefaults.cardColors(containerColor = Color(0xFF2B221A)),
    shape = RoundedCornerShape(20.dp),
    border = CardDefaults.outlinedCardBorder().copy(brush = SolidColor(AccentAmber.copy(alpha = 0.5f)))
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
    ) {
      Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
          imageVector = Icons.Default.VpnKey,
          contentDescription = null,
          tint = AccentAmber,
          modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
          text = "TraffMonetizer Token Required",
          style = MaterialTheme.typography.titleMedium.copy(fontSize = 14.sp, fontWeight = FontWeight.Bold),
          color = AccentAmber
        )
      }

      Spacer(modifier = Modifier.height(6.dp))

      Text(
        text = "To start monetizing bandwidth and running the client, configure your application token from the TraffMonetizer dashboard.",
        style = MaterialTheme.typography.bodyMedium.copy(fontSize = 13.sp),
        color = TextPrimary
      )

      Spacer(modifier = Modifier.height(12.dp))

      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        Button(
          onClick = onSetDemoToken,
          colors = ButtonDefaults.buttonColors(containerColor = PrimaryLavender, contentColor = OnPrimaryContainerLavender),
          shape = RoundedCornerShape(20.dp),
          modifier = Modifier.weight(1f)
        ) {
          Text("Use Demo Token", style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold))
        }

        Button(
          onClick = onGoSettings,
          colors = ButtonDefaults.buttonColors(containerColor = SurfaceElevated, contentColor = TextPrimary),
          shape = RoundedCornerShape(20.dp),
          modifier = Modifier.weight(1f)
        ) {
          Text("Enter Token", style = MaterialTheme.typography.labelMedium)
        }
      }
    }
  }
}
