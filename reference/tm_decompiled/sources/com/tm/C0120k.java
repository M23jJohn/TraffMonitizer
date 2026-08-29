package com.tm;

import java.util.Arrays;
import kotlin.UByte$$ExternalSyntheticBackport0;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.tm.k, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0120k extends AbstractC0193u3 {
    public final byte[] b;
    public final boolean c;
    public final String d;
    public final double e;
    public final double f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0120k(byte[] instanceId, boolean z, String statusMessage, double d, double d2) {
        super(EnumC0052a1.y, instanceId, statusMessage);
        Intrinsics.checkNotNullParameter(instanceId, "instanceId");
        Intrinsics.checkNotNullParameter(statusMessage, "statusMessage");
        this.b = instanceId;
        this.c = z;
        this.d = statusMessage;
        this.e = d;
        this.f = d2;
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
        if (!Intrinsics.areEqual(C0120k.class, obj != null ? obj.getClass() : null)) {
            return false;
        }
        if (obj == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.traffmonetizer.sdk.repository.api.command.BalanceResponseMessage");
        }
        C0120k c0120k = (C0120k) obj;
        return Arrays.equals(this.b, c0120k.b) && this.c == c0120k.c && Intrinsics.areEqual(this.d, c0120k.d) && this.e == c0120k.e && this.f == c0120k.f;
    }

    public final int hashCode() {
        return UByte$$ExternalSyntheticBackport0.m(this.f) + ((UByte$$ExternalSyntheticBackport0.m(this.e) + ((this.d.hashCode() + ((UByte$$ExternalSyntheticBackport0.m(this.c) + (Arrays.hashCode(this.b) * 31)) * 31)) * 31)) * 31);
    }

    public final String toString() {
        StringBuilder a = AbstractC0057b.a(this.b, new StringBuilder("BalanceResponseMessage(instanceId="), ", status=");
        a.append(this.c);
        a.append(", statusMessage=");
        a.append(this.d);
        a.append(", balance=");
        a.append(this.e);
        a.append(", last30Days=");
        a.append(this.f);
        a.append(')');
        return a.toString();
    }
}
