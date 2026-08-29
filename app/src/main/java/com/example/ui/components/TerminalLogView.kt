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
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.DeleteSweep
import androidx.compose.material.icons.filled.Terminal
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.LogEntry
import com.example.data.model.LogLevel
import com.example.ui.theme.AccentAmber
import com.example.ui.theme.AccentError
import com.example.ui.theme.AccentOnline
import com.example.ui.theme.AccentWarning
import com.example.ui.theme.PrimaryLavender
import com.example.ui.theme.SecondaryCyan
import com.example.ui.theme.SurfaceBorder
import com.example.ui.theme.SurfaceDark
import com.example.ui.theme.SurfaceElevated
import com.example.ui.theme.TerminalBg
import com.example.ui.theme.TerminalBorder
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

@Composable
fun TerminalLogView(
  logs: List<LogEntry>,
  onClearLogs: () -> Unit,
  modifier: Modifier = Modifier,
  showControls: Boolean = true
) {
  val context = LocalContext.current
  val listState = rememberLazyListState()
  var selectedFilter by remember { mutableStateOf<LogLevel?>(null) }

  val filteredLogs = remember(logs, selectedFilter) {
    if (selectedFilter == null) logs else logs.filter { it.level == selectedFilter }
  }

  LaunchedEffect(filteredLogs.size) {
    if (filteredLogs.isNotEmpty()) {
      listState.animateScrollToItem(filteredLogs.size - 1)
    }
  }

  Column(
    modifier = modifier
      .clip(RoundedCornerShape(20.dp))
      .background(TerminalBg)
      .border(1.dp, TerminalBorder, RoundedCornerShape(20.dp))
  ) {
    // Terminal Window Header Bar (like macOS/Linux terminal)
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .background(SurfaceDark)
        .padding(horizontal = 14.dp, vertical = 10.dp),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      Row(verticalAlignment = Alignment.CenterVertically) {
        // Window dots
        Box(modifier = Modifier.size(10.dp).clip(CircleShape).background(AccentError))
        Spacer(modifier = Modifier.width(6.dp))
        Box(modifier = Modifier.size(10.dp).clip(CircleShape).background(AccentWarning))
        Spacer(modifier = Modifier.width(6.dp))
        Box(modifier = Modifier.size(10.dp).clip(CircleShape).background(AccentOnline))

        Spacer(modifier = Modifier.width(12.dp))

        Icon(
          imageVector = Icons.Default.Terminal,
          contentDescription = "Console",
          tint = TextSecondary,
          modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
          text = "traffmonetizer/cli_v2:latest (stdout)",
          style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
          color = TextSecondary
        )
      }

      if (showControls) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          IconButton(
            onClick = {
              val logText = filteredLogs.joinToString("\n") { "[${it.timestamp}] [${it.level}] ${it.message}" }
              val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
              clipboard.setPrimaryClip(ClipData.newPlainText("TraffMonetizer Logs", logText))
              Toast.makeText(context, "Logs copied to clipboard", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier.size(32.dp).testTag("copy_logs_button")
          ) {
            Icon(
              imageVector = Icons.Default.ContentCopy,
              contentDescription = "Copy logs",
              tint = TextSecondary,
              modifier = Modifier.size(16.dp)
            )
          }

          IconButton(
            onClick = onClearLogs,
            modifier = Modifier.size(32.dp).testTag("clear_logs_button")
          ) {
            Icon(
              imageVector = Icons.Default.DeleteSweep,
              contentDescription = "Clear logs",
              tint = TextSecondary,
              modifier = Modifier.size(18.dp)
            )
          }
        }
      }
    }

    if (showControls) {
      // Filter Chips
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .background(TerminalBg)
          .padding(horizontal = 12.dp, vertical = 6.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        FilterChip(
          selected = selectedFilter == null,
          onClick = { selectedFilter = null },
          label = { Text("ALL (${logs.size})", style = MaterialTheme.typography.labelSmall) },
          colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = SurfaceElevated,
            selectedLabelColor = PrimaryLavender,
            containerColor = Color.Transparent,
            labelColor = TextMuted
          ),
          border = FilterChipDefaults.filterChipBorder(
            borderColor = SurfaceBorder,
            selectedBorderColor = PrimaryLavender,
            borderWidth = 1.dp,
            enabled = true,
            selected = selectedFilter == null
          )
        )

        FilterChip(
          selected = selectedFilter == LogLevel.DATA,
          onClick = { selectedFilter = if (selectedFilter == LogLevel.DATA) null else LogLevel.DATA },
          label = { Text("DATA", style = MaterialTheme.typography.labelSmall) },
          colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = SurfaceElevated,
            selectedLabelColor = SecondaryCyan,
            containerColor = Color.Transparent,
            labelColor = TextMuted
          )
        )

        FilterChip(
          selected = selectedFilter == LogLevel.INFO,
          onClick = { selectedFilter = if (selectedFilter == LogLevel.INFO) null else LogLevel.INFO },
          label = { Text("INFO", style = MaterialTheme.typography.labelSmall) },
          colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = SurfaceElevated,
            selectedLabelColor = TextPrimary,
            containerColor = Color.Transparent,
            labelColor = TextMuted
          )
        )

        FilterChip(
          selected = selectedFilter == LogLevel.WARN || selectedFilter == LogLevel.ERROR,
          onClick = { selectedFilter = if (selectedFilter == LogLevel.ERROR) null else LogLevel.ERROR },
          label = { Text("ERR/WARN", style = MaterialTheme.typography.labelSmall) },
          colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = SurfaceElevated,
            selectedLabelColor = AccentError,
            containerColor = Color.Transparent,
            labelColor = TextMuted
          )
        )
      }
    }

    // Monospace Console Body
    if (filteredLogs.isEmpty()) {
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .height(180.dp)
          .padding(20.dp),
        contentAlignment = Alignment.Center
      ) {
        Text(
          text = "$ docker logs tm\n(No active log stream. Start node to stream stdout)",
          style = MaterialTheme.typography.labelSmall.copy(fontSize = 12.sp),
          color = TextMuted
        )
      }
    } else {
      LazyColumn(
        state = listState,
        modifier = Modifier
          .fillMaxWidth()
          .weight(1f, fill = false)
          .height(280.dp)
          .padding(horizontal = 12.dp, vertical = 8.dp),
        contentPadding = PaddingValues(bottom = 12.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
      ) {
        items(filteredLogs, key = { it.id }) { entry ->
          val levelColor = when (entry.level) {
            LogLevel.SUCCESS -> AccentOnline
            LogLevel.DATA -> SecondaryCyan
            LogLevel.INFO -> PrimaryLavender
            LogLevel.WARN -> AccentAmber
            LogLevel.ERROR -> AccentError
          }

          val annotated = buildAnnotatedString {
            withStyle(SpanStyle(color = TextMuted, fontSize = 11.sp)) {
              append("[${entry.timestamp}] ")
            }
            withStyle(
              SpanStyle(
                color = levelColor,
                fontWeight = FontWeight.Bold,
                fontSize = 11.sp
              )
            ) {
              append("[${entry.level.name}] ")
            }
            withStyle(
              SpanStyle(
                color = if (entry.level == LogLevel.ERROR) AccentError else TextPrimary,
                fontSize = 12.sp
              )
            ) {
              append(entry.message)
            }
          }

          Text(
            text = annotated,
            style = MaterialTheme.typography.labelSmall.copy(
              fontFamily = FontFamily.Monospace,
              lineHeight = 16.sp
            ),
            modifier = Modifier.fillMaxWidth()
          )
        }
      }
    }
  }
}

