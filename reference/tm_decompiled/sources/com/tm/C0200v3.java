package com.tm;

import java.util.Map;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.tm.v3, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0200v3 extends I1 {
    public final int a;
    public final String b;
    public final int c;
    public final Map d;

    public C0200v3(int i, String host, int i2, Map map) {
        Intrinsics.checkNotNullParameter(host, "host");
        this.a = i;
        this.b = host;
        this.c = i2;
        this.d = map;
    }

    @Override // com.tm.I1
    public final int a() {
        return this.a;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0200v3)) {
            return false;
        }
        C0200v3 c0200v3 = (C0200v3) obj;
        return this.a == c0200v3.a && Intrinsics.areEqual(this.b, c0200v3.b) && this.c == c0200v3.c && Intrinsics.areEqual(this.d, c0200v3.d);
    }

    public final int hashCode() {
        int hashCode = (this.c + ((this.b.hashCode() + (this.a * 31)) * 31)) * 31;
        Map map = this.d;
        return hashCode + (map == null ? 0 : map.hashCode());
    }

    public final String toString() {
        return "TcpProxyConnectRequest(sessionId=" + this.a + ", host=" + this.b + ", port=" + this.c + ", features=" + this.d + ')';
    }
}
