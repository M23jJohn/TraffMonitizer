package com.example.ui.screens

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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Computer
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Dns
import androidx.compose.material.icons.filled.Hub
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.local.RemoteNodeEntity
import com.example.data.model.AppSettings
import com.example.data.model.NodeStatus
import com.example.data.model.TrafficStats
import com.example.service.TraffMonetizerEngine
import com.example.ui.components.AddRemoteNodeDialog
import com.example.ui.theme.AccentAmber
import com.example.ui.theme.AccentError
import com.example.ui.theme.AccentOnline
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
import java.util.Locale

@Composable
fun NodesScreen(
  localStatus: NodeStatus,
  localStats: TrafficStats,
  settings: AppSettings,
  remoteNodes: List<RemoteNodeEntity>,
  onAddRemoteNode: (RemoteNodeEntity) -> Unit,
  onDeleteRemoteNode: (Long) -> Unit,
  modifier: Modifier = Modifier
) {
  var showAddDialog by remember { mutableStateOf(false) }

  val totalRemoteTrafficMb = remoteNodes.sumOf { it.totalTrafficMb }
  val totalRemoteEarnings = remoteNodes.sumOf { it.estimatedEarningsUsd }

  val localTrafficMb = localStats.todayBytes / (1024.0 * 1024.0)
  val combinedTrafficMb = localTrafficMb + totalRemoteTrafficMb
  val combinedEarnings = localStats.estimatedEarningsUsd + totalRemoteEarnings

  Scaffold(
    modifier = modifier.fillMaxSize(),
    containerColor = BgDark,
    floatingActionButton = {
      FloatingActionButton(
        onClick = { showAddDialog = true },
        containerColor = PrimaryLavender,
        contentColor = OnPrimaryContainerLavender,
        shape = RoundedCornerShape(18.dp),
        modifier = Modifier.testTag("add_remote_node_fab")
      ) {
        Icon(Icons.Default.Add, contentDescription = "Add remote node")
      }
    }
  ) { padding ->
    LazyColumn(
      modifier = Modifier
        .fillMaxSize()
        .padding(padding)
        .padding(horizontal = 16.dp),
      contentPadding = PaddingValues(top = 16.dp, bottom = 80.dp),
      verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
      // Cluster Summary Header Card
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
                Box(
                  modifier = Modifier
                    .size(38.dp)
                    .clip(CircleShape)
                    .background(PrimaryLavender.copy(alpha = 0.15f)),
                  contentAlignment = Alignment.Center
                ) {
                  Icon(Icons.Default.Hub, contentDescription = null, tint = PrimaryLavender, modifier = Modifier.size(20.dp))
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                  Text(
                    text = "Node Cluster Fleet",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    color = TextPrimary
                  )
                  Text(
                    text = "${remoteNodes.size + 1} Total Active Nodes",
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 12.sp),
                    color = TextSecondary
                  )
                }
              }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
              Column(
                modifier = Modifier
                  .weight(1f)
                  .clip(RoundedCornerShape(14.dp))
                  .background(SurfaceElevated)
                  .padding(14.dp)
              ) {
                Text("Combined Shared", style = MaterialTheme.typography.bodyMedium.copy(fontSize = 11.sp), color = TextSecondary)
                Spacer(modifier = Modifier.height(4.dp))
                val totalGb = combinedTrafficMb / 1024.0
                Text(
                  text = String.format(Locale.US, "%.2f GB", totalGb),
                  style = MaterialTheme.typography.titleMedium.copy(fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold),
                  color = SecondaryCyan
                )
              }

              Column(
                modifier = Modifier
                  .weight(1f)
                  .clip(RoundedCornerShape(14.dp))
                  .background(SurfaceElevated)
                  .padding(14.dp)
              ) {
                Text("Combined Balance", style = MaterialTheme.typography.bodyMedium.copy(fontSize = 11.sp), color = TextSecondary)
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                  text = "$${String.format(Locale.US, "%.4f", combinedEarnings)}",
                  style = MaterialTheme.typography.titleMedium.copy(fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold),
                  color = AccentAmber
                )
              }
            }
          }
        }
      }

      // Section Title: Local Node
      item {
        Text(
          text = "Local Android Node",
          style = MaterialTheme.typography.titleMedium.copy(fontSize = 14.sp, fontWeight = FontWeight.Bold),
          color = TextSecondary,
          modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp)
        )
      }

      // Local Android Node Card
      item {
        Card(
          modifier = Modifier.fillMaxWidth(),
          colors = CardDefaults.cardColors(containerColor = SurfaceDark),
          shape = RoundedCornerShape(18.dp),
          border = CardDefaults.outlinedCardBorder().copy(brush = SolidColor(PrimaryLavender.copy(alpha = 0.4f)))
        ) {
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .padding(14.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
              Box(
                modifier = Modifier
                  .size(42.dp)
                  .clip(RoundedCornerShape(12.dp))
                  .background(PrimaryLavender.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
              ) {
                Icon(Icons.Default.PhoneAndroid, contentDescription = null, tint = PrimaryLavender, modifier = Modifier.size(24.dp))
              }
              Spacer(modifier = Modifier.width(12.dp))
              Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                  Text(
                    text = settings.deviceName,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    color = TextPrimary
                  )
                  Spacer(modifier = Modifier.width(6.dp))
                  Box(
                    modifier = Modifier
                      .clip(RoundedCornerShape(6.dp))
                      .background(PrimaryLavender.copy(alpha = 0.2f))
                      .padding(horizontal = 6.dp, vertical = 2.dp)
                  ) {
                    Text("HOST", style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold), color = PrimaryLavender)
                  }
                }
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                  text = "Shared: ${TraffMonetizerEngine.formatBytes(localStats.todayBytes)} • Est: $${String.format(Locale.US, "%.5f", localStats.estimatedEarningsUsd)}",
                  style = MaterialTheme.typography.bodyMedium.copy(fontSize = 12.sp),
                  color = TextSecondary
                )
              }
            }

            Box(
              modifier = Modifier
                .clip(CircleShape)
                .background(if (localStatus == NodeStatus.ONLINE) AccentOnline else AccentError)
                .size(10.dp)
            )
          }
        }
      }

      // Section Title: Remote Nodes
      item {
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp, vertical = 4.dp),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Text(
            text = "Remote Docker / VPS Fleet (${remoteNodes.size})",
            style = MaterialTheme.typography.titleMedium.copy(fontSize = 14.sp, fontWeight = FontWeight.Bold),
            color = TextSecondary
          )
        }
      }

      if (remoteNodes.isEmpty()) {
        item {
          Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = SurfaceDark),
            shape = RoundedCornerShape(18.dp),
            border = CardDefaults.outlinedCardBorder().copy(brush = SolidColor(SurfaceBorder))
          ) {
            Column(
              modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
              horizontalAlignment = Alignment.CenterHorizontally
            ) {
              Icon(Icons.Default.Dns, contentDescription = null, tint = TextMuted, modifier = Modifier.size(36.dp))
              Spacer(modifier = Modifier.height(8.dp))
              Text("No Remote Nodes Added", style = MaterialTheme.typography.titleMedium, color = TextPrimary)
              Spacer(modifier = Modifier.height(4.dp))
              Text(
                "Tap the '+' button to register external Docker CLI or VPS instances and track aggregate earnings in one place.",
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 12.sp),
                color = TextSecondary,
                modifier = Modifier.padding(horizontal = 16.dp)
              )
            }
          }
        }
      } else {
        items(remoteNodes, key = { it.id }) { node ->
          RemoteNodeItemCard(
            node = node,
            onDelete = { onDeleteRemoteNode(node.id) }
          )
        }
      }
    }
  }

  if (showAddDialog) {
    AddRemoteNodeDialog(
      onDismiss = { showAddDialog = false },
      onAddNode = { newNode ->
        onAddRemoteNode(newNode)
        showAddDialog = false
      }
    )
  }
}

@Composable
private fun RemoteNodeItemCard(
  node: RemoteNodeEntity,
  onDelete: () -> Unit
) {
  Card(
    modifier = Modifier.fillMaxWidth(),
    colors = CardDefaults.cardColors(containerColor = SurfaceDark),
    shape = RoundedCornerShape(18.dp),
    border = CardDefaults.outlinedCardBorder().copy(brush = SolidColor(SurfaceBorder))
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(14.dp),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
          modifier = Modifier
            .size(42.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(SecondaryCyan.copy(alpha = 0.15f)),
          contentAlignment = Alignment.Center
        ) {
          Icon(Icons.Default.Computer, contentDescription = null, tint = SecondaryCyan, modifier = Modifier.size(22.dp))
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
              text = node.name,
              style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
              color = TextPrimary
            )
            Spacer(modifier = Modifier.width(6.dp))
            Box(
              modifier = Modifier
                .clip(RoundedCornerShape(4.dp))
                .background(SurfaceElevated)
                .padding(horizontal = 6.dp, vertical = 2.dp)
            ) {
              Text(node.platform, style = MaterialTheme.typography.labelSmall, color = TextSecondary)
            }
          }
          Spacer(modifier = Modifier.height(2.dp))
          Text(
            text = "${node.ip} • Shared: ${String.format(Locale.US, "%.1f MB", node.totalTrafficMb)} • $${String.format(Locale.US, "%.4f", node.estimatedEarningsUsd)}",
            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 12.sp),
            color = TextSecondary
          )
        }
      }

      IconButton(onClick = onDelete) {
        Icon(Icons.Default.Delete, contentDescription = "Delete Node", tint = TextMuted, modifier = Modifier.size(18.dp))
      }
    }
  }
}

