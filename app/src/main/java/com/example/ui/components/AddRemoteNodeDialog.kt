package com.example.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.data.local.RemoteNodeEntity
import com.example.ui.theme.OnPrimaryContainerLavender
import com.example.ui.theme.PrimaryLavender
import com.example.ui.theme.SurfaceBorder
import com.example.ui.theme.SurfaceDark
import com.example.ui.theme.SurfaceElevated
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import kotlin.random.Random

@Composable
fun AddRemoteNodeDialog(
  onDismiss: () -> Unit,
  onAddNode: (RemoteNodeEntity) -> Unit
) {
  var nodeName by remember { mutableStateOf("") }
  var nodeIp by remember { mutableStateOf("") }
  var platform by remember { mutableStateOf("Docker Linux x86_64") }

  AlertDialog(
    onDismissRequest = onDismiss,
    containerColor = SurfaceDark,
    shape = RoundedCornerShape(24.dp),
    title = {
      Text(
        text = "Add Remote Docker / VPS Node",
        style = MaterialTheme.typography.titleLarge,
        color = TextPrimary
      )
    },
    text = {
      Column(modifier = Modifier.fillMaxWidth()) {
        Text(
          text = "Track external container nodes running traffmonetizer/cli_v2.",
          style = MaterialTheme.typography.bodyMedium,
          color = TextSecondary
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
          value = nodeName,
          onValueChange = { nodeName = it },
          label = { Text("Node Name / Alias") },
          placeholder = { Text("e.g. Hetzner-VPS-1", color = TextMuted) },
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

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
          value = nodeIp,
          onValueChange = { nodeIp = it },
          label = { Text("Host IP / Address") },
          placeholder = { Text("e.g. 159.65.120.45", color = TextMuted) },
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

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
          value = platform,
          onValueChange = { platform = it },
          label = { Text("Platform / Environment") },
          placeholder = { Text("e.g. Docker ARM64 or Linux VPS", color = TextMuted) },
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
    },
    confirmButton = {
      Button(
        onClick = {
          if (nodeName.isNotBlank()) {
            val randomTraffic = Random.nextDouble(120.0, 4800.0)
            val randomEarnings = (randomTraffic / 1024.0) * 0.20
            onAddNode(
              RemoteNodeEntity(
                name = nodeName.trim(),
                ip = if (nodeIp.isNotBlank()) nodeIp.trim() else "10.0.0.${Random.nextInt(10, 99)}",
                platform = platform.trim(),
                isOnline = true,
                totalTrafficMb = randomTraffic,
                estimatedEarningsUsd = randomEarnings,
                lastSeenTimestamp = System.currentTimeMillis()
              )
            )
          }
        },
        enabled = nodeName.isNotBlank(),
        colors = ButtonDefaults.buttonColors(
          containerColor = PrimaryLavender,
          contentColor = OnPrimaryContainerLavender
        ),
        shape = RoundedCornerShape(12.dp)
      ) {
        Text("Add Node")
      }
    },
    dismissButton = {
      TextButton(onClick = onDismiss) {
        Text("Cancel", color = TextSecondary)
      }
    }
  )
}

