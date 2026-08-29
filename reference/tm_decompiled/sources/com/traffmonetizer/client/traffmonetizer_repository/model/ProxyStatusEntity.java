package com.traffmonetizer.client.traffmonetizer_repository.model;

import androidx.core.app.NotificationCompat;
import com.traffmonetizer.sdk.model.ServicePaused;
import com.traffmonetizer.sdk.model.ServiceStarted;
import com.traffmonetizer.sdk.model.ServiceStatus;
import com.traffmonetizer.sdk.model.ServiceStopped;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ProxyStatusEntity.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u0000 \u00042\u00020\u0001:\u0004\u0004\u0005\u0006\u0007B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0003\b\t\n¨\u0006\u000b"}, d2 = {"Lcom/traffmonetizer/client/traffmonetizer_repository/model/ProxyStatusEntity;", "", "<init>", "()V", "Companion", "Started", "Stopped", "Paused", "Lcom/traffmonetizer/client/traffmonetizer_repository/model/ProxyStatusEntity$Paused;", "Lcom/traffmonetizer/client/traffmonetizer_repository/model/ProxyStatusEntity$Started;", "Lcom/traffmonetizer/client/traffmonetizer_repository/model/ProxyStatusEntity$Stopped;", "app_productionRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public abstract class ProxyStatusEntity {
    public static final int $stable = 0;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    public /* synthetic */ ProxyStatusEntity(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private ProxyStatusEntity() {
    }

    /* compiled from: ProxyStatusEntity.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/traffmonetizer/client/traffmonetizer_repository/model/ProxyStatusEntity$Companion;", "", "<init>", "()V", "fromSdk", "Lcom/traffmonetizer/client/traffmonetizer_repository/model/ProxyStatusEntity;", NotificationCompat.CATEGORY_STATUS, "Lcom/traffmonetizer/sdk/model/ServiceStatus;", "app_productionRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ProxyStatusEntity fromSdk(ServiceStatus status) {
            Intrinsics.checkNotNullParameter(status, "status");
            if (Intrinsics.areEqual(status, ServiceStarted.INSTANCE)) {
                return Started.INSTANCE;
            }
            if (Intrinsics.areEqual(status, ServiceStopped.INSTANCE)) {
                return Stopped.INSTANCE;
            }
            if (status instanceof ServicePaused) {
                return new Paused("status.reason");
            }
            throw new NoWhenBranchMatchedException();
        }
    }

    /* compiled from: ProxyStatusEntity.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/traffmonetizer/client/traffmonetizer_repository/model/ProxyStatusEntity$Started;", "Lcom/traffmonetizer/client/traffmonetizer_repository/model/ProxyStatusEntity;", "<init>", "()V", "app_productionRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Started extends ProxyStatusEntity {
        public static final int $stable = 0;
        public static final Started INSTANCE = new Started();

        private Started() {
            super(null);
        }
    }

    /* compiled from: ProxyStatusEntity.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/traffmonetizer/client/traffmonetizer_repository/model/ProxyStatusEntity$Stopped;", "Lcom/traffmonetizer/client/traffmonetizer_repository/model/ProxyStatusEntity;", "<init>", "()V", "app_productionRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Stopped extends ProxyStatusEntity {
        public static final int $stable = 0;
        public static final Stopped INSTANCE = new Stopped();

        private Stopped() {
            super(null);
        }
    }

    /* compiled from: ProxyStatusEntity.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/traffmonetizer/client/traffmonetizer_repository/model/ProxyStatusEntity$Paused;", "Lcom/traffmonetizer/client/traffmonetizer_repository/model/ProxyStatusEntity;", "reason", "", "<init>", "(Ljava/lang/String;)V", "getReason", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_productionRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Paused extends ProxyStatusEntity {
        public static final int $stable = 0;
        private final String reason;

        public static /* synthetic */ Paused copy$default(Paused paused, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = paused.reason;
            }
            return paused.copy(str);
        }

        /* renamed from: component1, reason: from getter */
        public final String getReason() {
            return this.reason;
        }

        public final Paused copy(String reason) {
            Intrinsics.checkNotNullParameter(reason, "reason");
            return new Paused(reason);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Paused) && Intrinsics.areEqual(this.reason, ((Paused) other).reason);
        }

        public int hashCode() {
            return this.reason.hashCode();
        }

        public String toString() {
            return "Paused(reason=" + this.reason + ')';
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Paused(String reason) {
            super(null);
            Intrinsics.checkNotNullParameter(reason, "reason");
            this.reason = reason;
        }

        public final String getReason() {
            return this.reason;
        }
    }
}
