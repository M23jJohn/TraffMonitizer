package com.traffmonetizer.client.ui.main.home;

import java.text.DecimalFormat;
import kotlin.Metadata;

/* compiled from: StatsViewModel.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0006HÆ\u0003J'\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001J\t\u0010\u001f\u001a\u00020\u000fHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u000e\u001a\u00020\u000f8F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0012\u001a\u00020\u000f8F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0011R\u0011\u0010\u0014\u001a\u00020\u000f8F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0011¨\u0006 "}, d2 = {"Lcom/traffmonetizer/client/ui/main/home/TraffStatsEntity;", "", "totalBalance", "", "earned", "traffic", "", "<init>", "(DDJ)V", "getTotalBalance", "()D", "getEarned", "getTraffic", "()J", "balanceFormatted", "", "getBalanceFormatted", "()Ljava/lang/String;", "earnedFormatted", "getEarnedFormatted", "trafficFormatted", "getTrafficFormatted", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_productionRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class TraffStatsEntity {
    public static final int $stable = 0;
    private final double earned;
    private final double totalBalance;
    private final long traffic;

    public static /* synthetic */ TraffStatsEntity copy$default(TraffStatsEntity traffStatsEntity, double d, double d2, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            d = traffStatsEntity.totalBalance;
        }
        double d3 = d;
        if ((i & 2) != 0) {
            d2 = traffStatsEntity.earned;
        }
        double d4 = d2;
        if ((i & 4) != 0) {
            j = traffStatsEntity.traffic;
        }
        return traffStatsEntity.copy(d3, d4, j);
    }

    /* renamed from: component1, reason: from getter */
    public final double getTotalBalance() {
        return this.totalBalance;
    }

    /* renamed from: component2, reason: from getter */
    public final double getEarned() {
        return this.earned;
    }

    /* renamed from: component3, reason: from getter */
    public final long getTraffic() {
        return this.traffic;
    }

    public final TraffStatsEntity copy(double totalBalance, double earned, long traffic) {
        return new TraffStatsEntity(totalBalance, earned, traffic);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TraffStatsEntity)) {
            return false;
        }
        TraffStatsEntity traffStatsEntity = (TraffStatsEntity) other;
        return Double.compare(this.totalBalance, traffStatsEntity.totalBalance) == 0 && Double.compare(this.earned, traffStatsEntity.earned) == 0 && this.traffic == traffStatsEntity.traffic;
    }

    public int hashCode() {
        return (((TraffStatsEntity$$ExternalSyntheticBackport0.m(this.totalBalance) * 31) + TraffStatsEntity$$ExternalSyntheticBackport0.m(this.earned)) * 31) + TraffStatsEntity$$ExternalSyntheticBackport0.m(this.traffic);
    }

    public String toString() {
        return "TraffStatsEntity(totalBalance=" + this.totalBalance + ", earned=" + this.earned + ", traffic=" + this.traffic + ')';
    }

    public TraffStatsEntity(double d, double d2, long j) {
        this.totalBalance = d;
        this.earned = d2;
        this.traffic = j;
    }

    public final double getTotalBalance() {
        return this.totalBalance;
    }

    public final double getEarned() {
        return this.earned;
    }

    public final long getTraffic() {
        return this.traffic;
    }

    public final String getBalanceFormatted() {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        if (this.totalBalance < 1.0d) {
            return "¢" + decimalFormat.format(this.totalBalance * 100);
        }
        return "$" + decimalFormat.format(this.totalBalance);
    }

    public final String getEarnedFormatted() {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        if (this.earned < 1.0d) {
            return "¢" + decimalFormat.format(this.earned * 100);
        }
        return "$" + decimalFormat.format(this.earned);
    }

    public final String getTrafficFormatted() {
        long j = this.traffic;
        double d = j / 1024.0d;
        double d2 = (j / 1024.0d) / 1024.0d;
        double d3 = ((j / 1024.0d) / 1024.0d) / 1024.0d;
        double d4 = (((j / 1024.0d) / 1024.0d) / 1024.0d) / 1024.0d;
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        if (d4 > 1.0d) {
            return decimalFormat.format(d4) + " TB";
        }
        if (d3 > 1.0d) {
            return decimalFormat.format(d3) + " GB";
        }
        if (d2 > 1.0d) {
            return decimalFormat.format(d2) + " MB";
        }
        return decimalFormat.format(d) + " KB";
    }
}
