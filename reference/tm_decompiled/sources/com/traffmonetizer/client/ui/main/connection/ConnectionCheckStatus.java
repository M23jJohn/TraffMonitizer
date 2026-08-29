package com.traffmonetizer.client.ui.main.connection;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: ConnectionCheckViewModel.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lcom/traffmonetizer/client/ui/main/connection/ConnectionCheckStatus;", "", "<init>", "(Ljava/lang/String;I)V", "OK", "FAILURE", "PROGRESS", "EMPTY", "app_productionRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ConnectionCheckStatus {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ ConnectionCheckStatus[] $VALUES;
    public static final ConnectionCheckStatus OK = new ConnectionCheckStatus("OK", 0);
    public static final ConnectionCheckStatus FAILURE = new ConnectionCheckStatus("FAILURE", 1);
    public static final ConnectionCheckStatus PROGRESS = new ConnectionCheckStatus("PROGRESS", 2);
    public static final ConnectionCheckStatus EMPTY = new ConnectionCheckStatus("EMPTY", 3);

    private static final /* synthetic */ ConnectionCheckStatus[] $values() {
        return new ConnectionCheckStatus[]{OK, FAILURE, PROGRESS, EMPTY};
    }

    public static EnumEntries<ConnectionCheckStatus> getEntries() {
        return $ENTRIES;
    }

    public static ConnectionCheckStatus valueOf(String str) {
        return (ConnectionCheckStatus) Enum.valueOf(ConnectionCheckStatus.class, str);
    }

    public static ConnectionCheckStatus[] values() {
        return (ConnectionCheckStatus[]) $VALUES.clone();
    }

    private ConnectionCheckStatus(String str, int i) {
    }

    static {
        ConnectionCheckStatus[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }
}
