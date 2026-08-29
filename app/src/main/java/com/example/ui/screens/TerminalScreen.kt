package com.example.ui.screens

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.LogEntry
import com.example.data.model.NodeStatus
import com.example.ui.components.TerminalLogView
import com.example.ui.theme.AccentError
import com.example.ui.theme.BgDark
import com.example.ui.theme.OnPrimaryContainerLavender
import com.example.ui.theme.PrimaryLavender
import com.example.ui.theme.SurfaceBorder
import com.example.ui.theme.SurfaceDark
import com.example.ui.theme.SurfaceElevated
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

@Composable
fun TerminalScreen(
  logs: List<LogEntry>,
  status: NodeStatus,
  onToggleNode: () -> Unit,
  onClearLogs: () -> Unit,
  modifier: Modifier = Modifier
) {
  val context = LocalContext.current
  var searchQuery by remember { mutableStateOf("") }

  val filteredLogs = remember(logs, searchQuery) {
    if (searchQuery.isBlank()) {
      logs
    } else {
      logs.filter { it.message.contains(searchQuery, ignoreCase = true) || it.tag.contains(searchQuery, ignoreCase = true) }
    }
  }

  Column(
    modifier = modifier
      .fillMaxSize()
      .background(BgDark)
      .padding(16.dp),
    verticalArrangement = Arrangement.spacedBy(14.dp)
  ) {
    // Header controls
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
        Column {
          Text(
            text = "CLI stdout Stream",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            color = TextPrimary
          )
          Text(
            text = "Buffer: ${logs.size} lines • Mode: Direct stdout",
            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 12.sp),
            color = TextSecondary
          )
        }

        val isRunning = status == NodeStatus.ONLINE || status == NodeStatus.CONNECTING
        Button(
          onClick = onToggleNode,
          colors = ButtonDefaults.buttonColors(
            containerColor = if (isRunning) AccentError.copy(alpha = 0.2f) else PrimaryLavender,
            contentColor = if (isRunning) AccentError else OnPrimaryContainerLavender
          ),
          shape = RoundedCornerShape(16.dp),
          modifier = Modifier.testTag("terminal_quick_toggle_button")
        ) {
          Icon(
            imageVector = if (isRunning) Icons.Default.Stop else Icons.Default.PlayArrow,
            contentDescription = null,
            modifier = Modifier.size(16.dp)
          )
          Spacer(modifier = Modifier.width(6.dp))
          Text(if (isRunning) "Stop" else "Start", style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold))
        }
      }
    }

    // Search filter input
    OutlinedTextField(
      value = searchQuery,
      onValueChange = { searchQuery = it },
      placeholder = { Text("Filter log output (e.g. RELAY, Handshake, err...)", style = MaterialTheme.typography.bodyMedium.copy(color = TextMuted)) },
      leadingIcon = {
        Icon(Icons.Default.Search, contentDescription = null, tint = TextSecondary, modifier = Modifier.size(18.dp))
      },
      trailingIcon = {
        if (searchQuery.isNotEmpty()) {
          IconButton(onClick = { searchQuery = "" }) {
            Icon(Icons.Default.Clear, contentDescription = "Clear search", tint = TextSecondary, modifier = Modifier.size(16.dp))
          }
        }
      },
      singleLine = true,
      modifier = Modifier
        .fillMaxWidth()
        .height(52.dp),
      colors = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = PrimaryLavender,
        unfocusedBorderColor = SurfaceBorder,
        focusedTextColor = TextPrimary,
        unfocusedTextColor = TextPrimary,
        focusedContainerColor = SurfaceElevated,
        unfocusedContainerColor = SurfaceElevated
      ),
      shape = RoundedCornerShape(16.dp)
    )

    // Full screen expandable terminal
    TerminalLogView(
      logs = filteredLogs,
      onClearLogs = onClearLogs,
      showControls = true,
      modifier = Modifier
        .fillMaxWidth()
        .weight(1f)
    )
  }
}

