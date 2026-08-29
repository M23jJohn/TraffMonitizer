package com.tm;

import kotlin.UByte$$ExternalSyntheticBackport0;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.tm.i, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0106i {
    public final double a;
    public final double b;

    public C0106i(double d, double d2) {
        this.a = d;
        this.b = d2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0106i)) {
            return false;
        }
        C0106i c0106i = (C0106i) obj;
        return Intrinsics.areEqual((Object) Double.valueOf(this.a), (Object) Double.valueOf(c0106i.a)) && Intrinsics.areEqual((Object) Double.valueOf(this.b), (Object) Double.valueOf(c0106i.b));
    }

    public final int hashCode() {
        return UByte$$ExternalSyntheticBackport0.m(this.b) + (UByte$$ExternalSyntheticBackport0.m(this.a) * 31);
    }

    public final String toString() {
        return "Balance(balance=" + this.a + ", last30Days=" + this.b + ')';
    }
}
