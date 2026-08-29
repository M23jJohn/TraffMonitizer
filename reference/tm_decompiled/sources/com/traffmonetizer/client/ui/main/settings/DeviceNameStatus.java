package com.traffmonetizer.client.ui.main.settings;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: SettingsViewModel.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lcom/traffmonetizer/client/ui/main/settings/DeviceNameStatus;", "", "<init>", "(Ljava/lang/String;I)V", "LOADING", "SHOWING", "EDITING", "SUBMITTING", "app_productionRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class DeviceNameStatus {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ DeviceNameStatus[] $VALUES;
    public static final DeviceNameStatus LOADING = new DeviceNameStatus("LOADING", 0);
    public static final DeviceNameStatus SHOWING = new DeviceNameStatus("SHOWING", 1);
    public static final DeviceNameStatus EDITING = new DeviceNameStatus("EDITING", 2);
    public static final DeviceNameStatus SUBMITTING = new DeviceNameStatus("SUBMITTING", 3);

    private static final /* synthetic */ DeviceNameStatus[] $values() {
        return new DeviceNameStatus[]{LOADING, SHOWING, EDITING, SUBMITTING};
    }

    public static EnumEntries<DeviceNameStatus> getEntries() {
        return $ENTRIES;
    }

    public static DeviceNameStatus valueOf(String str) {
        return (DeviceNameStatus) Enum.valueOf(DeviceNameStatus.class, str);
    }

    public static DeviceNameStatus[] values() {
        return (DeviceNameStatus[]) $VALUES.clone();
    }

    private DeviceNameStatus(String str, int i) {
    }

    static {
        DeviceNameStatus[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }
}
