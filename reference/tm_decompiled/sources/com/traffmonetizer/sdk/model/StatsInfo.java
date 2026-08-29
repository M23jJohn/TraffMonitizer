package com.traffmonetizer.sdk.model;

import kotlin.Metadata;
import kotlin.UByte$$ExternalSyntheticBackport0;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J'\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0016"}, d2 = {"Lcom/traffmonetizer/sdk/model/StatsInfo;", "", "inboundTraffic", "", "outboundTraffic", "requestsCount", "(JJJ)V", "getInboundTraffic", "()J", "getOutboundTraffic", "getRequestsCount", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "TraffmonetizerSDK-v1.2.11_internalRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class StatsInfo {
    private final long inboundTraffic;
    private final long outboundTraffic;
    private final long requestsCount;

    public StatsInfo(long j, long j2, long j3) {
        this.inboundTraffic = j;
        this.outboundTraffic = j2;
        this.requestsCount = j3;
    }

    public static /* synthetic */ StatsInfo copy$default(StatsInfo statsInfo, long j, long j2, long j3, int i, Object obj) {
        if ((i & 1) != 0) {
            j = statsInfo.inboundTraffic;
        }
        long j4 = j;
        if ((i & 2) != 0) {
            j2 = statsInfo.outboundTraffic;
        }
        long j5 = j2;
        if ((i & 4) != 0) {
            j3 = statsInfo.requestsCount;
        }
        return statsInfo.copy(j4, j5, j3);
    }

    /* renamed from: component1, reason: from getter */
    public final long getInboundTraffic() {
        return this.inboundTraffic;
    }

    /* renamed from: component2, reason: from getter */
    public final long getOutboundTraffic() {
        return this.outboundTraffic;
    }

    /* renamed from: component3, reason: from getter */
    public final long getRequestsCount() {
        return this.requestsCount;
    }

    public final StatsInfo copy(long inboundTraffic, long outboundTraffic, long requestsCount) {
        return new StatsInfo(inboundTraffic, outboundTraffic, requestsCount);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof StatsInfo)) {
            return false;
        }
        StatsInfo statsInfo = (StatsInfo) other;
        return this.inboundTraffic == statsInfo.inboundTraffic && this.outboundTraffic == statsInfo.outboundTraffic && this.requestsCount == statsInfo.requestsCount;
    }

    public final long getInboundTraffic() {
        return this.inboundTraffic;
    }

    public final long getOutboundTraffic() {
        return this.outboundTraffic;
    }

    public final long getRequestsCount() {
        return this.requestsCount;
    }

    public int hashCode() {
        return UByte$$ExternalSyntheticBackport0.m(this.requestsCount) + ((UByte$$ExternalSyntheticBackport0.m(this.outboundTraffic) + (UByte$$ExternalSyntheticBackport0.m(this.inboundTraffic) * 31)) * 31);
    }

    public String toString() {
        return "StatsInfo(inboundTraffic=" + this.inboundTraffic + ", outboundTraffic=" + this.outboundTraffic + ", requestsCount=" + this.requestsCount + ')';
    }
}
