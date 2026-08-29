package com.tm;

import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.tm.c0, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0065c0 extends AbstractC0079e0 {
    public final Throwable b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0065c0(Throwable cause) {
        super("ConnectionFailed(" + cause + ')');
        Intrinsics.checkNotNullParameter(cause, "cause");
        this.b = cause;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof C0065c0) && Intrinsics.areEqual(this.b, ((C0065c0) obj).b);
    }

    public final int hashCode() {
        return this.b.hashCode();
    }

    public final String toString() {
        return "ConnectionFailed(cause=" + this.b + ')';
    }
}
