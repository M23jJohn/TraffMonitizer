package com.example.ui.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.PauseCircle
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.PowerSettingsNew
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.NodeStatus
import com.example.service.TraffMonetizerEngine
import com.example.ui.theme.AccentAmber
import com.example.ui.theme.AccentError
import com.example.ui.theme.AccentOnline
import com.example.ui.theme.AccentWarning
import com.example.ui.theme.PrimaryLavender
import com.example.ui.theme.SecondaryCyan
import com.example.ui.theme.SurfaceBorder
import com.example.ui.theme.SurfaceDark
import com.example.ui.theme.SurfaceElevated
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

@Composable
fun SpeedGaugeCard(
  title: String,
  bps: Long,
  isUpload: Boolean,
  modifier: Modifier = Modifier
) {
  val accentColor = if (isUpload) PrimaryLavender else SecondaryCyan
  val icon = if (isUpload) Icons.Default.ArrowUpward else Icons.Default.ArrowDownward
  val speedText = TraffMonetizerEngine.formatSpeed(bps)

  // Dynamic progress (up to 1.5 MB/s nominal scale)
  val progress = (bps.toFloat() / (1.5f * 1024f * 1024f)).coerceIn(0.04f, 1f)

  Card(
    modifier = modifier.testTag(if (isUpload) "upload_speed_card" else "download_speed_card"),
    colors = CardDefaults.cardColors(containerColor = SurfaceDark),
    shape = RoundedCornerShape(24.dp),
    border = CardDefaults.outlinedCardBorder().copy(brush = SolidColor(SurfaceBorder))
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
    ) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Box(
            modifier = Modifier
              .size(32.dp)
              .clip(CircleShape)
              .background(accentColor.copy(alpha = 0.15f)),
            contentAlignment = Alignment.Center
          ) {
            Icon(
              imageVector = icon,
              contentDescription = title,
              tint = accentColor,
              modifier = Modifier.size(18.dp)
            )
          }
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = title,
            style = MaterialTheme.typography.titleMedium.copy(fontSize = 14.sp),
            color = TextSecondary
          )
        }

        Text(
          text = if (bps > 0) "ACTIVE" else "IDLE",
          style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
          color = if (bps > 0) accentColor else TextMuted
        )
      }

      Spacer(modifier = Modifier.height(12.dp))

      Text(
        text = speedText,
        style = MaterialTheme.typography.headlineMedium.copy(
          fontFamily = FontFamily.Monospace,
          fontWeight = FontWeight.Bold,
          letterSpacing = (-0.5).sp
        ),
        color = TextPrimary
      )

      Spacer(modifier = Modifier.height(10.dp))

      LinearProgressIndicator(
        progress = { if (bps > 0) progress else 0f },
        modifier = Modifier
          .fillMaxWidth()
          .height(6.dp)
          .clip(RoundedCornerShape(3.dp)),
        color = accentColor,
        trackColor = SurfaceElevated
      )
    }
  }
}

@Composable
fun MasterNodePulseButton(
  status: NodeStatus,
  onToggleClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  val infiniteTransition = rememberInfiniteTransition(label = "pulse")
  val pulseScale by infiniteTransition.animateFloat(
    initialValue = 1f,
    targetValue = 1.18f,
    animationSpec = infiniteRepeatable(
      animation = tween(1200, easing = FastOutSlowInEasing),
      repeatMode = RepeatMode.Reverse
    ),
    label = "pulse_scale"
  )

  val pulseAlpha by infiniteTransition.animateFloat(
    initialValue = 0.4f,
    targetValue = 0.05f,
    animationSpec = infiniteRepeatable(
      animation = tween(1200, easing = LinearEasing),
      repeatMode = RepeatMode.Restart
    ),
    label = "pulse_alpha"
  )

  val (statusColor, statusText, statusIcon) = when (status) {
    NodeStatus.ONLINE -> Triple(PrimaryLavender, "NODE ONLINE", Icons.Default.PowerSettingsNew)
    NodeStatus.CONNECTING -> Triple(SecondaryCyan, "CONNECTING...", Icons.Default.Refresh)
    NodeStatus.PAUSED_BATTERY -> Triple(AccentWarning, "PAUSED (BATTERY)", Icons.Default.PauseCircle)
    NodeStatus.PAUSED_WIFI -> Triple(AccentWarning, "PAUSED (CELLULAR)", Icons.Default.PauseCircle)
    NodeStatus.ERROR -> Triple(AccentError, "NODE ERROR", Icons.Default.Error)
    NodeStatus.STOPPED -> Triple(TextMuted, "TAP TO START NODE", Icons.Default.PlayArrow)
  }

  val isRunning = status == NodeStatus.ONLINE || status == NodeStatus.CONNECTING

  Column(
    modifier = modifier.fillMaxWidth(),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Box(
      modifier = Modifier
        .size(170.dp)
        .testTag("master_node_toggle_button")
        .clickable { onToggleClick() },
      contentAlignment = Alignment.Center
    ) {
      // Outer animated pulse ring when running
      if (isRunning) {
        Box(
          modifier = Modifier
            .size(160.dp)
            .scale(pulseScale)
            .clip(CircleShape)
            .background(statusColor.copy(alpha = pulseAlpha))
        )
      }

      // Medium glowing ring
      Box(
        modifier = Modifier
          .size(140.dp)
          .clip(CircleShape)
          .background(
            Brush.radialGradient(
              colors = listOf(
                statusColor.copy(alpha = if (isRunning) 0.25f else 0.08f),
                Color.Transparent
              )
            )
          )
          .border(
            width = if (isRunning) 2.dp else 1.dp,
            color = statusColor.copy(alpha = if (isRunning) 0.8f else 0.3f),
            shape = CircleShape
          )
      )

      // Inner Core Button
      Box(
        modifier = Modifier
          .size(108.dp)
          .clip(CircleShape)
          .background(
            Brush.verticalGradient(
              listOf(SurfaceElevated, SurfaceDark)
            )
          )
          .border(1.5.dp, SurfaceBorder, CircleShape),
        contentAlignment = Alignment.Center
      ) {
        Column(
          horizontalAlignment = Alignment.CenterHorizontally,
          verticalArrangement = Arrangement.Center
        ) {
          Icon(
            imageVector = statusIcon,
            contentDescription = "Toggle Node",
            tint = statusColor,
            modifier = Modifier.size(38.dp)
          )
          Spacer(modifier = Modifier.height(4.dp))
          Text(
            text = if (isRunning) "STOP" else "START",
            style = MaterialTheme.typography.labelLarge.copy(
              fontWeight = FontWeight.ExtraBold,
              letterSpacing = 1.sp
            ),
            color = TextPrimary
          )
        }
      }
    }

    Spacer(modifier = Modifier.height(12.dp))

    Row(
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.Center
    ) {
      Box(
        modifier = Modifier
          .size(10.dp)
          .clip(CircleShape)
          .background(if (status == NodeStatus.ONLINE) AccentOnline else statusColor)
      )
      Spacer(modifier = Modifier.width(8.dp))
      Text(
        text = statusText,
        style = MaterialTheme.typography.titleMedium.copy(
          fontFamily = FontFamily.Monospace,
          fontSize = 14.sp,
          fontWeight = FontWeight.Bold,
          letterSpacing = 0.5.sp
        ),
        color = statusColor
      )
    }
  }
}

