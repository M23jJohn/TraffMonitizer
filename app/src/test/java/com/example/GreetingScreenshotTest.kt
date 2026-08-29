package com.example

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onRoot
import com.example.data.model.AppSettings
import com.example.data.model.NetworkInfo
import com.example.data.model.NodeStatus
import com.example.data.model.TrafficStats
import com.example.ui.screens.DashboardScreen
import com.example.ui.theme.MyApplicationTheme
import com.github.takahirom.roborazzi.RobolectricDeviceQualifiers
import com.github.takahirom.roborazzi.captureRoboImage
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = RobolectricDeviceQualifiers.Pixel8, sdk = [36])
class GreetingScreenshotTest {

  @get:Rule val composeTestRule = createComposeRule()

  @Test
  fun dashboard_screenshot() {
    composeTestRule.setContent {
      MyApplicationTheme {
        DashboardScreen(
          status = NodeStatus.ONLINE,
          stats = TrafficStats(
            currentDownloadBps = 145000L,
            currentUploadBps = 320000L,
            todayBytes = 142000000L,
            lifetimeBytes = 520000000L,
            totalRequestsServed = 84,
            estimatedEarningsUsd = 0.0284,
            sessionDurationSeconds = 3600L
          ),
          networkInfo = NetworkInfo(
            ipAddress = "192.168.1.100",
            isp = "Residential ISP",
            latencyMs = 24
          ),
          settings = AppSettings(
            token = "tm_test_token_12345",
            deviceName = "Pixel-8-Node"
          ),
          logs = emptyList(),
          onToggleNode = {},
          onRefreshNetwork = {},
          onSetDemoToken = {},
          onNavigateToSettings = {},
          onNavigateToTerminal = {},
          onClearLogs = {}
        )
      }
    }

    composeTestRule.onRoot().captureRoboImage(filePath = "src/test/screenshots/greeting.png")
  }
}
