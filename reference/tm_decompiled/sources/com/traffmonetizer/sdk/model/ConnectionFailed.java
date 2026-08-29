package com.traffmonetizer.sdk.model;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lcom/traffmonetizer/sdk/model/ConnectionFailed;", "Lcom/traffmonetizer/sdk/model/ConnectionStatus;", "cause", "", "(Ljava/lang/Throwable;)V", "getCause", "()Ljava/lang/Throwable;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "TraffmonetizerSDK-v1.2.11_internalRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class ConnectionFailed extends ConnectionStatus {
    private final Throwable cause;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConnectionFailed(Throwable cause) {
        super("ConnectionFailed(" + cause + ')', null);
        Intrinsics.checkNotNullParameter(cause, "cause");
        this.cause = cause;
    }

    public static /* synthetic */ ConnectionFailed copy$default(ConnectionFailed connectionFailed, Throwable th, int i, Object obj) {
        if ((i & 1) != 0) {
            th = connectionFailed.cause;
        }
        return connectionFailed.copy(th);
    }

    /* renamed from: component1, reason: from getter */
    public final Throwable getCause() {
        return this.cause;
    }

    public final ConnectionFailed copy(Throwable cause) {
        Intrinsics.checkNotNullParameter(cause, "cause");
        return new ConnectionFailed(cause);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof ConnectionFailed) && Intrinsics.areEqual(this.cause, ((ConnectionFailed) other).cause);
    }

    public final Throwable getCause() {
        return this.cause;
    }

    public int hashCode() {
        return this.cause.hashCode();
    }

    public String toString() {
        return "ConnectionFailed(cause=" + this.cause + ')';
    }
}
