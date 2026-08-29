package com.tm;

import kotlin.UByte$$ExternalSyntheticBackport0;

/* renamed from: com.tm.s3, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0179s3 {
    public final long a;
    public final long b;
    public final long c;

    public C0179s3(long j, long j2, long j3) {
        this.a = j;
        this.b = j2;
        this.c = j3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0179s3)) {
            return false;
        }
        C0179s3 c0179s3 = (C0179s3) obj;
        return this.a == c0179s3.a && this.b == c0179s3.b && this.c == c0179s3.c;
    }

    public final int hashCode() {
        return UByte$$ExternalSyntheticBackport0.m(this.c) + ((UByte$$ExternalSyntheticBackport0.m(this.b) + (UByte$$ExternalSyntheticBackport0.m(this.a) * 31)) * 31);
    }

    public final String toString() {
        return "Stats(inboundTraffic=" + this.a + ", outboundTraffic=" + this.b + ", requestsCount=" + this.c + ')';
    }
}
