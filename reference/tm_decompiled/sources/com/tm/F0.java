package com.tm;

import kotlin.UByte$$ExternalSyntheticBackport0;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class F0 {
    public final long a;
    public final String b;

    public F0(long j, String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        this.a = j;
        this.b = message;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof F0)) {
            return false;
        }
        F0 f0 = (F0) obj;
        return this.a == f0.a && Intrinsics.areEqual(this.b, f0.b);
    }

    public final int hashCode() {
        return this.b.hashCode() + (UByte$$ExternalSyntheticBackport0.m(this.a) * 31);
    }

    public final String toString() {
        return "DebugMessage(timestamp=" + this.a + ", message=" + this.b + ')';
    }
}
