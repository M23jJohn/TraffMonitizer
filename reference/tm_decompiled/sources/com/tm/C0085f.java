package com.tm;

import java.util.Arrays;
import kotlin.UByte$$ExternalSyntheticBackport0;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.tm.f, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0085f extends V2 {
    public final byte[] b;
    public final int c;
    public final long d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0085f(int i, long j, byte[] instanceId) {
        super(EnumC0052a1.p, instanceId);
        Intrinsics.checkNotNullParameter(instanceId, "instanceId");
        this.b = instanceId;
        this.c = i;
        this.d = j;
    }

    @Override // com.tm.Z0
    public final byte[] a() {
        return this.b;
    }

    @Override // com.tm.V2, com.tm.Z0
    public final byte[] b() {
        byte[] b = super.b();
        long j = this.d;
        byte[] bArr = new byte[8];
        for (int i = 0; i < 8; i++) {
            bArr[i] = (byte) (j >> (i * 8));
        }
        return ArraysKt.plus(b, bArr);
    }

    @Override // com.tm.V2
    public final int c() {
        return this.c;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!Intrinsics.areEqual(C0085f.class, obj != null ? obj.getClass() : null)) {
            return false;
        }
        if (obj == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.traffmonetizer.sdk.repository.api.command.AckMessage");
        }
        C0085f c0085f = (C0085f) obj;
        return Arrays.equals(this.b, c0085f.b) && this.c == c0085f.c && this.d == c0085f.d;
    }

    public final int hashCode() {
        return UByte$$ExternalSyntheticBackport0.m(this.d) + (((Arrays.hashCode(this.b) * 31) + this.c) * 31);
    }

    public final String toString() {
        StringBuilder a = AbstractC0057b.a(this.b, new StringBuilder("AckMessage(instanceId="), ", sessionId=");
        a.append(this.c);
        a.append(", ackBytes=");
        a.append(this.d);
        a.append(')');
        return a.toString();
    }
}
