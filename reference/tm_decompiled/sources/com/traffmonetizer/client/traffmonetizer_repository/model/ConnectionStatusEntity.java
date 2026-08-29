package com.traffmonetizer.client.traffmonetizer_repository.model;

import androidx.core.app.NotificationCompat;
import com.traffmonetizer.sdk.model.ConnectionConnected;
import com.traffmonetizer.sdk.model.ConnectionDisconnected;
import com.traffmonetizer.sdk.model.ConnectionFailed;
import com.traffmonetizer.sdk.model.ConnectionStatus;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ConnectionStatusEntity.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u0000 \u00042\u00020\u0001:\u0007\u0004\u0005\u0006\u0007\b\t\nB\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0006\u000b\f\r\u000e\u000f\u0010¨\u0006\u0011"}, d2 = {"Lcom/traffmonetizer/client/traffmonetizer_repository/model/ConnectionStatusEntity;", "", "<init>", "()V", "Companion", "Connected", "Disconnected", "LoadBalancerResolving", "SocketConnecting", "HelloConnecting", "Failed", "Lcom/traffmonetizer/client/traffmonetizer_repository/model/ConnectionStatusEntity$Connected;", "Lcom/traffmonetizer/client/traffmonetizer_repository/model/ConnectionStatusEntity$Disconnected;", "Lcom/traffmonetizer/client/traffmonetizer_repository/model/ConnectionStatusEntity$Failed;", "Lcom/traffmonetizer/client/traffmonetizer_repository/model/ConnectionStatusEntity$HelloConnecting;", "Lcom/traffmonetizer/client/traffmonetizer_repository/model/ConnectionStatusEntity$LoadBalancerResolving;", "Lcom/traffmonetizer/client/traffmonetizer_repository/model/ConnectionStatusEntity$SocketConnecting;", "app_productionRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public abstract class ConnectionStatusEntity {
    public static final int $stable = 0;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    public /* synthetic */ ConnectionStatusEntity(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* compiled from: ConnectionStatusEntity.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/traffmonetizer/client/traffmonetizer_repository/model/ConnectionStatusEntity$Companion;", "", "<init>", "()V", "fromSdk", "Lcom/traffmonetizer/client/traffmonetizer_repository/model/ConnectionStatusEntity;", NotificationCompat.CATEGORY_STATUS, "Lcom/traffmonetizer/sdk/model/ConnectionStatus;", "app_productionRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ConnectionStatusEntity fromSdk(ConnectionStatus status) {
            Intrinsics.checkNotNullParameter(status, "status");
            if (Intrinsics.areEqual(status, ConnectionConnected.INSTANCE)) {
                return Connected.INSTANCE;
            }
            if (Intrinsics.areEqual(status, ConnectionDisconnected.INSTANCE)) {
                return Disconnected.INSTANCE;
            }
            if (status instanceof ConnectionFailed) {
                return new Failed(((ConnectionFailed) status).getCause());
            }
            if (Intrinsics.areEqual(status, com.traffmonetizer.sdk.model.HelloConnecting.INSTANCE)) {
                return HelloConnecting.INSTANCE;
            }
            if (Intrinsics.areEqual(status, com.traffmonetizer.sdk.model.LoadBalancerResolving.INSTANCE)) {
                return LoadBalancerResolving.INSTANCE;
            }
            if (Intrinsics.areEqual(status, com.traffmonetizer.sdk.model.SocketConnecting.INSTANCE)) {
                return SocketConnecting.INSTANCE;
            }
            throw new NoWhenBranchMatchedException();
        }
    }

    private ConnectionStatusEntity() {
    }

    /* compiled from: ConnectionStatusEntity.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/traffmonetizer/client/traffmonetizer_repository/model/ConnectionStatusEntity$Connected;", "Lcom/traffmonetizer/client/traffmonetizer_repository/model/ConnectionStatusEntity;", "<init>", "()V", "app_productionRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Connected extends ConnectionStatusEntity {
        public static final int $stable = 0;
        public static final Connected INSTANCE = new Connected();

        private Connected() {
            super(null);
        }
    }

    /* compiled from: ConnectionStatusEntity.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/traffmonetizer/client/traffmonetizer_repository/model/ConnectionStatusEntity$Disconnected;", "Lcom/traffmonetizer/client/traffmonetizer_repository/model/ConnectionStatusEntity;", "<init>", "()V", "app_productionRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Disconnected extends ConnectionStatusEntity {
        public static final int $stable = 0;
        public static final Disconnected INSTANCE = new Disconnected();

        private Disconnected() {
            super(null);
        }
    }

    /* compiled from: ConnectionStatusEntity.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/traffmonetizer/client/traffmonetizer_repository/model/ConnectionStatusEntity$LoadBalancerResolving;", "Lcom/traffmonetizer/client/traffmonetizer_repository/model/ConnectionStatusEntity;", "<init>", "()V", "app_productionRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class LoadBalancerResolving extends ConnectionStatusEntity {
        public static final int $stable = 0;
        public static final LoadBalancerResolving INSTANCE = new LoadBalancerResolving();

        private LoadBalancerResolving() {
            super(null);
        }
    }

    /* compiled from: ConnectionStatusEntity.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/traffmonetizer/client/traffmonetizer_repository/model/ConnectionStatusEntity$SocketConnecting;", "Lcom/traffmonetizer/client/traffmonetizer_repository/model/ConnectionStatusEntity;", "<init>", "()V", "app_productionRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class SocketConnecting extends ConnectionStatusEntity {
        public static final int $stable = 0;
        public static final SocketConnecting INSTANCE = new SocketConnecting();

        private SocketConnecting() {
            super(null);
        }
    }

    /* compiled from: ConnectionStatusEntity.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/traffmonetizer/client/traffmonetizer_repository/model/ConnectionStatusEntity$HelloConnecting;", "Lcom/traffmonetizer/client/traffmonetizer_repository/model/ConnectionStatusEntity;", "<init>", "()V", "app_productionRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class HelloConnecting extends ConnectionStatusEntity {
        public static final int $stable = 0;
        public static final HelloConnecting INSTANCE = new HelloConnecting();

        private HelloConnecting() {
            super(null);
        }
    }

    /* compiled from: ConnectionStatusEntity.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/traffmonetizer/client/traffmonetizer_repository/model/ConnectionStatusEntity$Failed;", "Lcom/traffmonetizer/client/traffmonetizer_repository/model/ConnectionStatusEntity;", "cause", "", "<init>", "(Ljava/lang/Throwable;)V", "getCause", "()Ljava/lang/Throwable;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "app_productionRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Failed extends ConnectionStatusEntity {
        public static final int $stable = 8;
        private final Throwable cause;

        public static /* synthetic */ Failed copy$default(Failed failed, Throwable th, int i, Object obj) {
            if ((i & 1) != 0) {
                th = failed.cause;
            }
            return failed.copy(th);
        }

        /* renamed from: component1, reason: from getter */
        public final Throwable getCause() {
            return this.cause;
        }

        public final Failed copy(Throwable cause) {
            Intrinsics.checkNotNullParameter(cause, "cause");
            return new Failed(cause);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Failed) && Intrinsics.areEqual(this.cause, ((Failed) other).cause);
        }

        public int hashCode() {
            return this.cause.hashCode();
        }

        public String toString() {
            return "Failed(cause=" + this.cause + ')';
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Failed(Throwable cause) {
            super(null);
            Intrinsics.checkNotNullParameter(cause, "cause");
            this.cause = cause;
        }

        public final Throwable getCause() {
            return this.cause;
        }
    }
}
