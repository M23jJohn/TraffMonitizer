package com.tm;

import java.util.Arrays;
import kotlin.UByte$$ExternalSyntheticBackport0;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.tm.r3, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0172r3 extends AbstractC0193u3 {
    public final byte[] b;
    public final boolean c;
    public final String d;
    public final long e;
    public final long f;
    public final long g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0172r3(byte[] instanceId, boolean z, String statusMessage, long j, long j2, long j3) {
        super(EnumC0052a1.x, instanceId, statusMessage);
        Intrinsics.checkNotNullParameter(instanceId, "instanceId");
        Intrinsics.checkNotNullParameter(statusMessage, "statusMessage");
        this.b = instanceId;
        this.c = z;
        this.d = statusMessage;
        this.e = j;
        this.f = j2;
        this.g = j3;
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
        if (!Intrinsics.areEqual(C0172r3.class, obj != null ? obj.getClass() : null)) {
            return false;
        }
        if (obj == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.traffmonetizer.sdk.repository.api.command.StatResponseMessage");
        }
        C0172r3 c0172r3 = (C0172r3) obj;
        return Arrays.equals(this.b, c0172r3.b) && this.c == c0172r3.c && Intrinsics.areEqual(this.d, c0172r3.d) && this.e == c0172r3.e && this.f == c0172r3.f && this.g == c0172r3.g;
    }

    public final int hashCode() {
        return UByte$$ExternalSyntheticBackport0.m(this.g) + ((UByte$$ExternalSyntheticBackport0.m(this.f) + ((UByte$$ExternalSyntheticBackport0.m(this.e) + ((this.d.hashCode() + ((UByte$$ExternalSyntheticBackport0.m(this.c) + (Arrays.hashCode(this.b) * 31)) * 31)) * 31)) * 31)) * 31);
    }

    public final String toString() {
        StringBuilder a = AbstractC0057b.a(this.b, new StringBuilder("StatResponseMessage(instanceId="), ", status=");
        a.append(this.c);
        a.append(", statusMessage=");
        a.append(this.d);
        a.append(", inboundTraffic=");
        a.append(this.e);
        a.append(", outboundTraffic=");
        a.append(this.f);
        a.append(", requestsCount=");
        a.append(this.g);
        a.append(')');
        return a.toString();
    }
}
