package com.tm;

import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class S5 extends I1 {
    public final int a;
    public final String b;
    public final int c;

    public S5(int i, int i2, String host) {
        Intrinsics.checkNotNullParameter(host, "host");
        this.a = i;
        this.b = host;
        this.c = i2;
    }

    @Override // com.tm.I1
    public final int a() {
        return this.a;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof S5)) {
            return false;
        }
        S5 s5 = (S5) obj;
        return this.a == s5.a && Intrinsics.areEqual(this.b, s5.b) && this.c == s5.c;
    }

    public final int hashCode() {
        return this.c + ((this.b.hashCode() + (this.a * 31)) * 31);
    }

    public final String toString() {
        return "UdpProxyConnectRequest(sessionId=" + this.a + ", host=" + this.b + ", port=" + this.c + ')';
    }
}
