package com.traffmonetizer.client.ui.main.connection;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: ConnectionCheckViewModel.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\t\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, d2 = {"Lcom/traffmonetizer/client/ui/main/connection/ConnectionCheckStep;", "", "<init>", "(Ljava/lang/String;I)V", "INTERNET_CONNECTION", "LOAD_BALANCER", "SERVER_CONNECTION", "TOKEN_CHECK", "NO_ERROR", "ERROR", "app_productionRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ConnectionCheckStep {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ ConnectionCheckStep[] $VALUES;
    public static final ConnectionCheckStep INTERNET_CONNECTION = new ConnectionCheckStep("INTERNET_CONNECTION", 0);
    public static final ConnectionCheckStep LOAD_BALANCER = new ConnectionCheckStep("LOAD_BALANCER", 1);
    public static final ConnectionCheckStep SERVER_CONNECTION = new ConnectionCheckStep("SERVER_CONNECTION", 2);
    public static final ConnectionCheckStep TOKEN_CHECK = new ConnectionCheckStep("TOKEN_CHECK", 3);
    public static final ConnectionCheckStep NO_ERROR = new ConnectionCheckStep("NO_ERROR", 4);
    public static final ConnectionCheckStep ERROR = new ConnectionCheckStep("ERROR", 5);

    private static final /* synthetic */ ConnectionCheckStep[] $values() {
        return new ConnectionCheckStep[]{INTERNET_CONNECTION, LOAD_BALANCER, SERVER_CONNECTION, TOKEN_CHECK, NO_ERROR, ERROR};
    }

    public static EnumEntries<ConnectionCheckStep> getEntries() {
        return $ENTRIES;
    }

    public static ConnectionCheckStep valueOf(String str) {
        return (ConnectionCheckStep) Enum.valueOf(ConnectionCheckStep.class, str);
    }

    public static ConnectionCheckStep[] values() {
        return (ConnectionCheckStep[]) $VALUES.clone();
    }

    private ConnectionCheckStep(String str, int i) {
    }

    static {
        ConnectionCheckStep[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }
}
