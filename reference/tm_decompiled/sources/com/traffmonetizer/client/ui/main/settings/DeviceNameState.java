package com.traffmonetizer.client.ui.main.settings;

import androidx.autofill.HintConstants;
import androidx.core.app.NotificationCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SettingsViewModel.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001f\u0010\u000e\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lcom/traffmonetizer/client/ui/main/settings/DeviceNameState;", "", HintConstants.AUTOFILL_HINT_NAME, "", NotificationCompat.CATEGORY_STATUS, "Lcom/traffmonetizer/client/ui/main/settings/DeviceNameStatus;", "<init>", "(Ljava/lang/String;Lcom/traffmonetizer/client/ui/main/settings/DeviceNameStatus;)V", "getName", "()Ljava/lang/String;", "getStatus", "()Lcom/traffmonetizer/client/ui/main/settings/DeviceNameStatus;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_productionRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class DeviceNameState {
    public static final int $stable = 0;
    private final String name;
    private final DeviceNameStatus status;

    public static /* synthetic */ DeviceNameState copy$default(DeviceNameState deviceNameState, String str, DeviceNameStatus deviceNameStatus, int i, Object obj) {
        if ((i & 1) != 0) {
            str = deviceNameState.name;
        }
        if ((i & 2) != 0) {
            deviceNameStatus = deviceNameState.status;
        }
        return deviceNameState.copy(str, deviceNameStatus);
    }

    /* renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* renamed from: component2, reason: from getter */
    public final DeviceNameStatus getStatus() {
        return this.status;
    }

    public final DeviceNameState copy(String name, DeviceNameStatus status) {
        Intrinsics.checkNotNullParameter(status, "status");
        return new DeviceNameState(name, status);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DeviceNameState)) {
            return false;
        }
        DeviceNameState deviceNameState = (DeviceNameState) other;
        return Intrinsics.areEqual(this.name, deviceNameState.name) && this.status == deviceNameState.status;
    }

    public int hashCode() {
        String str = this.name;
        return ((str == null ? 0 : str.hashCode()) * 31) + this.status.hashCode();
    }

    public String toString() {
        return "DeviceNameState(name=" + this.name + ", status=" + this.status + ')';
    }

    public DeviceNameState(String str, DeviceNameStatus status) {
        Intrinsics.checkNotNullParameter(status, "status");
        this.name = str;
        this.status = status;
    }

    public final String getName() {
        return this.name;
    }

    public final DeviceNameStatus getStatus() {
        return this.status;
    }
}
