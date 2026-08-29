package com.tm;

/* loaded from: classes3.dex */
public final class N1 {
    public final int a;

    public N1(int i) {
        this.a = i;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof N1) && this.a == ((N1) obj).a;
    }

    public final int hashCode() {
        return this.a;
    }

    public final String toString() {
        return "ProxySessionEnd(sessionId=" + this.a + ')';
    }
}
