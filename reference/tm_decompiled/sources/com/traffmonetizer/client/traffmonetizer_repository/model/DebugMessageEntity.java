package com.traffmonetizer.client.traffmonetizer_repository.model;

import com.traffmonetizer.client.ui.main.home.TraffStatsEntity$$ExternalSyntheticBackport0;
import com.traffmonetizer.sdk.model.DebugMessage;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DebugMessageEntity.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0087\b\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/traffmonetizer/client/traffmonetizer_repository/model/DebugMessageEntity;", "", "timestamp", "", "message", "", "<init>", "(JLjava/lang/String;)V", "getTimestamp", "()J", "getMessage", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "Companion", "app_productionRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class DebugMessageEntity {
    public static final int $stable = 0;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String message;
    private final long timestamp;

    public static /* synthetic */ DebugMessageEntity copy$default(DebugMessageEntity debugMessageEntity, long j, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            j = debugMessageEntity.timestamp;
        }
        if ((i & 2) != 0) {
            str = debugMessageEntity.message;
        }
        return debugMessageEntity.copy(j, str);
    }

    /* renamed from: component1, reason: from getter */
    public final long getTimestamp() {
        return this.timestamp;
    }

    /* renamed from: component2, reason: from getter */
    public final String getMessage() {
        return this.message;
    }

    public final DebugMessageEntity copy(long timestamp, String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        return new DebugMessageEntity(timestamp, message);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DebugMessageEntity)) {
            return false;
        }
        DebugMessageEntity debugMessageEntity = (DebugMessageEntity) other;
        return this.timestamp == debugMessageEntity.timestamp && Intrinsics.areEqual(this.message, debugMessageEntity.message);
    }

    public int hashCode() {
        return (TraffStatsEntity$$ExternalSyntheticBackport0.m(this.timestamp) * 31) + this.message.hashCode();
    }

    public String toString() {
        return "DebugMessageEntity(timestamp=" + this.timestamp + ", message=" + this.message + ')';
    }

    public DebugMessageEntity(long j, String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        this.timestamp = j;
        this.message = message;
    }

    public final long getTimestamp() {
        return this.timestamp;
    }

    public final String getMessage() {
        return this.message;
    }

    /* compiled from: DebugMessageEntity.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/traffmonetizer/client/traffmonetizer_repository/model/DebugMessageEntity$Companion;", "", "<init>", "()V", "fromSdk", "Lcom/traffmonetizer/client/traffmonetizer_repository/model/DebugMessageEntity;", "message", "Lcom/traffmonetizer/sdk/model/DebugMessage;", "app_productionRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final DebugMessageEntity fromSdk(DebugMessage message) {
            Intrinsics.checkNotNullParameter(message, "message");
            return new DebugMessageEntity(message.getTimestamp(), message.getMessage());
        }
    }
}
