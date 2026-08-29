package com.tm;

import java.util.Arrays;
import kotlin.UByte$$ExternalSyntheticBackport0;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class P0 extends AbstractC0193u3 {
    public final byte[] b;
    public final boolean c;
    public final String d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public P0(byte[] instanceId, boolean z, String statusMessage) {
        super(EnumC0052a1.q, instanceId, statusMessage);
        Intrinsics.checkNotNullParameter(instanceId, "instanceId");
        Intrinsics.checkNotNullParameter(statusMessage, "statusMessage");
        this.b = instanceId;
        this.c = z;
        this.d = statusMessage;
    }

    @Override // com.tm.Z0
    public final byte[] a() {
        return this.b;
    }

    @Override // com.tm.AbstractC0193u3
    public final boolean c() {
        return this.c;
    }

    @Override // com.tm.AbstractC0193u3
    public final String d() {
        return this.d;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!Intrinsics.areEqual(P0.class, obj != null ? obj.getClass() : null)) {
            return false;
        }
        if (obj == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.traffmonetizer.sdk.repository.api.command.HelloResponseMessage");
        }
        P0 p0 = (P0) obj;
        return Arrays.equals(this.b, p0.b) && this.c == p0.c && Intrinsics.areEqual(this.d, p0.d);
    }

    public final int hashCode() {
        return this.d.hashCode() + ((UByte$$ExternalSyntheticBackport0.m(this.c) + (Arrays.hashCode(this.b) * 31)) * 31);
    }

    public final String toString() {
        StringBuilder a = AbstractC0057b.a(this.b, new StringBuilder("HelloResponseMessage(instanceId="), ", status=");
        a.append(this.c);
        a.append(", statusMessage=");
        a.append(this.d);
        a.append(')');
        return a.toString();
    }
}
