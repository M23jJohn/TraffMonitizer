package com.tm;

import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class K1 {
    public final int a;
    public final byte[] b;
    public final long c;

    public K1(int i, long j, byte[] packet) {
        Intrinsics.checkNotNullParameter(packet, "packet");
        this.a = i;
        this.b = packet;
        this.c = j;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!Intrinsics.areEqual(K1.class, obj != null ? obj.getClass() : null)) {
            return false;
        }
        if (obj == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.traffmonetizer.sdk.repository.repository.ProxyPacket");
        }
        K1 k1 = (K1) obj;
        return this.a == k1.a && Arrays.equals(this.b, k1.b);
    }

    public final int hashCode() {
        return Arrays.hashCode(this.b) + (this.a * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("ProxyPacket(sessionId=");
        sb.append(this.a);
        sb.append(", packet=");
        StringBuilder a = AbstractC0057b.a(this.b, sb, ", timestamp=");
        a.append(this.c);
        a.append(')');
        return a.toString();
    }
}
