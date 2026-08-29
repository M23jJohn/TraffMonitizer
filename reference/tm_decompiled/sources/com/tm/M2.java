package com.tm;

import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class M2 extends P2 {
    public final EnumC0123k2 b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public M2(EnumC0123k2 reason) {
        super("ServicePaused");
        Intrinsics.checkNotNullParameter(reason, "reason");
        this.b = reason;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof M2) && this.b == ((M2) obj).b;
    }

    public final int hashCode() {
        return this.b.hashCode();
    }

    public final String toString() {
        return "ServicePaused(reason=" + this.b + ')';
    }
}
