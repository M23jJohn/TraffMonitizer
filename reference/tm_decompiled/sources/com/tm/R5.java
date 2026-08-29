package com.tm;

import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class R5 extends V2 {
    public final byte[] b;
    public final int c;
    public final String d;
    public final int e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public R5(byte[] instanceId, int i, String host, int i2) {
        super(EnumC0052a1.n, instanceId);
        Intrinsics.checkNotNullParameter(instanceId, "instanceId");
        Intrinsics.checkNotNullParameter(host, "host");
        this.b = instanceId;
        this.c = i;
        this.d = host;
        this.e = i2;
    }

    @Override // com.tm.Z0
    public final byte[] a() {
        return this.b;
    }

    @Override // com.tm.V2
    public final int c() {
        return this.c;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!Intrinsics.areEqual(R5.class, obj != null ? obj.getClass() : null)) {
            return false;
        }
        if (obj == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.traffmonetizer.sdk.repository.api.command.UdpBindMessage");
        }
        R5 r5 = (R5) obj;
        return Arrays.equals(this.b, r5.b) && this.c == r5.c && Intrinsics.areEqual(this.d, r5.d) && this.e == r5.e;
    }

    public final int hashCode() {
        return ((this.d.hashCode() + (((Arrays.hashCode(this.b) * 31) + this.c) * 31)) * 31) + this.e;
    }

    public final String toString() {
        StringBuilder a = AbstractC0057b.a(this.b, new StringBuilder("UdpBindMessage(instanceId="), ", sessionId=");
        a.append(this.c);
        a.append(", host=");
        a.append(this.d);
        a.append(", port=");
        a.append(this.e);
        a.append(')');
        return a.toString();
    }
}
