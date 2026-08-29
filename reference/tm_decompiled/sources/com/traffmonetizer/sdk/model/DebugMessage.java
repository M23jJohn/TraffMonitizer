package com.traffmonetizer.sdk.model;

import kotlin.Metadata;
import kotlin.UByte$$ExternalSyntheticBackport0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Lcom/traffmonetizer/sdk/model/DebugMessage;", "", "timestamp", "", "message", "", "(JLjava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "getTimestamp", "()J", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "TraffmonetizerSDK-v1.2.11_internalRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class DebugMessage {
    private final String message;
    private final long timestamp;

    public DebugMessage(long j, String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        this.timestamp = j;
        this.message = message;
    }

    public static /* synthetic */ DebugMessage copy$default(DebugMessage debugMessage, long j, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            j = debugMessage.timestamp;
        }
        if ((i & 2) != 0) {
            str = debugMessage.message;
        }
        return debugMessage.copy(j, str);
    }

    /* renamed from: component1, reason: from getter */
    public final long getTimestamp() {
        return this.timestamp;
    }

    /* renamed from: component2, reason: from getter */
    public final String getMessage() {
        return this.message;
    }

    public final DebugMessage copy(long timestamp, String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        return new DebugMessage(timestamp, message);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DebugMessage)) {
            return false;
        }
        DebugMessage debugMessage = (DebugMessage) other;
        return this.timestamp == debugMessage.timestamp && Intrinsics.areEqual(this.message, debugMessage.message);
    }

    public final String getMessage() {
        return this.message;
    }

    public final long getTimestamp() {
        return this.timestamp;
    }

    public int hashCode() {
        return this.message.hashCode() + (UByte$$ExternalSyntheticBackport0.m(this.timestamp) * 31);
    }

    public String toString() {
        return "DebugMessage(timestamp=" + this.timestamp + ", message=" + this.message + ')';
    }
}
