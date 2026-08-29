package com.example

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Hub
import androidx.compose.material.icons.filled.Layers
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Terminal
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.ui.MainViewModel
import com.example.ui.screens.DashboardScreen
import com.example.ui.screens.DockerGenScreen
import com.example.ui.screens.NodesScreen
import com.example.ui.screens.SettingsScreen
import com.example.ui.screens.TerminalScreen
import com.example.ui.theme.BgDark
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.theme.NavBg
import com.example.ui.theme.NavPillActive
import com.example.ui.theme.PrimaryContainerLavender
import com.example.ui.theme.PrimaryLavender
import com.example.ui.theme.SurfaceBorder
import com.example.ui.theme.TextSecondary

enum class AppTab(val title: String, val icon: ImageVector) {
  DASHBOARD("Overview", Icons.Default.Dashboard),
  TERMINAL("Console", Icons.Default.Terminal),
  NODES("Fleet", Icons.Default.Hub),
  DOCKER("Docker", Icons.Default.Layers),
  SETTINGS("Settings", Icons.Default.Settings)
}

class MainActivity : ComponentActivity() {
  private val viewModel: MainViewModel by viewModels()

  private val requestPermissionLauncher = registerForActivityResult(
    ActivityResultContracts.RequestPermission()
  ) { _ -> }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
      if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
        requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
      }
    }

    setContent {
      MyApplicationTheme {
        MainAppContent(viewModel = viewModel)
      }
    }
  }

  override fun onResume() {
    super.onResume()
    viewModel.refreshNetwork()
  }
}

@Composable
fun MainAppContent(viewModel: MainViewModel) {
  var currentTabIndex by rememberSaveable { mutableIntStateOf(0) }
  val tabs = AppTab.entries

  val status by viewModel.status.collectAsStateWithLifecycle()
  val stats by viewModel.stats.collectAsStateWithLifecycle()
  val networkInfo by viewModel.networkInfo.collectAsStateWithLifecycle()
  val settings by viewModel.settings.collectAsStateWithLifecycle()
  val logs by viewModel.logs.collectAsStateWithLifecycle()
  val remoteNodes by viewModel.remoteNodes.collectAsStateWithLifecycle()

  Scaffold(
    modifier = Modifier
      .fillMaxSize()
      .background(BgDark)
      .windowInsetsPadding(WindowInsets.statusBars),
    bottomBar = {
      NavigationBar(
        containerColor = NavBg,
        contentColor = PrimaryLavender,
        tonalElevation = 0.dp,
        modifier = Modifier
          .windowInsetsPadding(WindowInsets.navigationBars)
          .testTag("main_bottom_nav_bar")
      ) {
        tabs.forEachIndexed { index, tab ->
          val isSelected = currentTabIndex == index
          NavigationBarItem(
            selected = isSelected,
            onClick = { currentTabIndex = index },
            icon = {
              Icon(
                imageVector = tab.icon,
                contentDescription = tab.title,
                modifier = Modifier.size(22.dp)
              )
            },
            label = {
              Text(
                text = tab.title,
                style = MaterialTheme.typography.labelSmall.copy(
                  fontSize = 11.sp,
                  fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                )
              )
            },
            colors = NavigationBarItemDefaults.colors(
              selectedIconColor = PrimaryContainerLavender,
              selectedTextColor = PrimaryContainerLavender,
              indicatorColor = NavPillActive,
              unselectedIconColor = TextSecondary,
              unselectedTextColor = TextSecondary
            ),
            modifier = Modifier.testTag("nav_tab_${tab.name.lowercase()}")
          )
        }
      }
    }
  ) { innerPadding ->
    Box(
      modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding)
    ) {
      AnimatedContent(
        targetState = currentTabIndex,
        transitionSpec = { fadeIn() togetherWith fadeOut() },
        label = "tab_transition"
      ) { tabIndex ->
        when (tabs[tabIndex]) {
          AppTab.DASHBOARD -> DashboardScreen(
            status = status,
            stats = stats,
            networkInfo = networkInfo,
            settings = settings,
            logs = logs,
            onToggleNode = { viewModel.toggleNode() },
            onRefreshNetwork = { viewModel.refreshNetwork() },
            onSetDemoToken = { viewModel.setDemoToken() },
            onNavigateToSettings = { currentTabIndex = AppTab.SETTINGS.ordinal },
            onNavigateToTerminal = { currentTabIndex = AppTab.TERMINAL.ordinal },
            onClearLogs = { viewModel.clearLogs() }
          )

          AppTab.TERMINAL -> TerminalScreen(
            logs = logs,
            status = status,
            onToggleNode = { viewModel.toggleNode() },
            onClearLogs = { viewModel.clearLogs() }
          )

          AppTab.NODES -> NodesScreen(
            localStatus = status,
            localStats = stats,
            settings = settings,
            remoteNodes = remoteNodes,
            onAddRemoteNode = { viewModel.addRemoteNode(it) },
            onDeleteRemoteNode = { viewModel.deleteRemoteNode(it) }
          )

          AppTab.DOCKER -> DockerGenScreen(
            settings = settings
          )

          AppTab.SETTINGS -> SettingsScreen(
            settings = settings,
            onSaveSettings = { viewModel.saveSettings(it) },
            onClearHistory = { viewModel.clearHistory() }
          )
        }
      }
    }
  }
}

