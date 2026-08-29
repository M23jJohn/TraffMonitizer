package com.traffmonetizer.client.ui.main.settings;

import com.traffmonetizer.client.ui.main.home.TraffStatsEntity$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SettingsViewModel.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0006HÆ\u0003J'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00032\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0018"}, d2 = {"Lcom/traffmonetizer/client/ui/main/settings/SettingsState;", "", "wifiOnly", "", "debugMode", "deviceName", "Lcom/traffmonetizer/client/ui/main/settings/DeviceNameState;", "<init>", "(ZZLcom/traffmonetizer/client/ui/main/settings/DeviceNameState;)V", "getWifiOnly", "()Z", "getDebugMode", "getDeviceName", "()Lcom/traffmonetizer/client/ui/main/settings/DeviceNameState;", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "", "app_productionRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class SettingsState {
    public static final int $stable = 0;
    private final boolean debugMode;
    private final DeviceNameState deviceName;
    private final boolean wifiOnly;

    public static /* synthetic */ SettingsState copy$default(SettingsState settingsState, boolean z, boolean z2, DeviceNameState deviceNameState, int i, Object obj) {
        if ((i & 1) != 0) {
            z = settingsState.wifiOnly;
        }
        if ((i & 2) != 0) {
            z2 = settingsState.debugMode;
        }
        if ((i & 4) != 0) {
            deviceNameState = settingsState.deviceName;
        }
        return settingsState.copy(z, z2, deviceNameState);
    }

    /* renamed from: component1, reason: from getter */
    public final boolean getWifiOnly() {
        return this.wifiOnly;
    }

    /* renamed from: component2, reason: from getter */
    public final boolean getDebugMode() {
        return this.debugMode;
    }

    /* renamed from: component3, reason: from getter */
    public final DeviceNameState getDeviceName() {
        return this.deviceName;
    }

    public final SettingsState copy(boolean wifiOnly, boolean debugMode, DeviceNameState deviceName) {
        Intrinsics.checkNotNullParameter(deviceName, "deviceName");
        return new SettingsState(wifiOnly, debugMode, deviceName);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SettingsState)) {
            return false;
        }
        SettingsState settingsState = (SettingsState) other;
        return this.wifiOnly == settingsState.wifiOnly && this.debugMode == settingsState.debugMode && Intrinsics.areEqual(this.deviceName, settingsState.deviceName);
    }

    public int hashCode() {
        return (((TraffStatsEntity$$ExternalSyntheticBackport0.m(this.wifiOnly) * 31) + TraffStatsEntity$$ExternalSyntheticBackport0.m(this.debugMode)) * 31) + this.deviceName.hashCode();
    }

    public String toString() {
        return "SettingsState(wifiOnly=" + this.wifiOnly + ", debugMode=" + this.debugMode + ", deviceName=" + this.deviceName + ')';
    }

    public SettingsState(boolean z, boolean z2, DeviceNameState deviceName) {
        Intrinsics.checkNotNullParameter(deviceName, "deviceName");
        this.wifiOnly = z;
        this.debugMode = z2;
        this.deviceName = deviceName;
    }

    public final boolean getWifiOnly() {
        return this.wifiOnly;
    }

    public final boolean getDebugMode() {
        return this.debugMode;
    }

    public final DeviceNameState getDeviceName() {
        return this.deviceName;
    }
}
