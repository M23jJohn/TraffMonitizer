package com.tm;

import java.util.Arrays;
import kotlin.UByte$$ExternalSyntheticBackport0;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.tm.s, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0175s extends X2 {
    public final byte[] b;
    public final boolean c;
    public final String d;
    public final int e;
    public final byte[] f;
    public final int g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0175s(byte[] instanceId, boolean z, String statusMessage, int i, byte[] remoteIp, int i2) {
        super(EnumC0052a1.v, instanceId, z, statusMessage);
        Intrinsics.checkNotNullParameter(instanceId, "instanceId");
        Intrinsics.checkNotNullParameter(statusMessage, "statusMessage");
        Intrinsics.checkNotNullParameter(remoteIp, "remoteIp");
        this.b = instanceId;
        this.c = z;
        this.d = statusMessage;
        this.e = i;
        this.f = remoteIp;
        this.g = i2;
    }

    @Override // com.tm.Z0
    public final byte[] a() {
        return this.b;
    }

    @Override // com.tm.X2, com.tm.AbstractC0193u3, com.tm.Z0
    public final byte[] b() {
        return ArraysKt.plus(ArraysKt.plus(ArraysKt.plus(super.b(), AbstractC0099h.a(this.f.length)), this.f), AbstractC0099h.a(this.g));
    }

    @Override // com.tm.AbstractC0193u3
    public final boolean c() {
        return this.c;
    }

    @Override // com.tm.AbstractC0193u3
    public final String d() {
        return this.d;
    }

    @Override // com.tm.X2
    public final int e() {
        return this.e;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!Intrinsics.areEqual(C0175s.class, obj != null ? obj.getClass() : null)) {
            return false;
        }
        if (obj == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.traffmonetizer.sdk.repository.api.command.ConnectResponseMessage");
        }
        C0175s c0175s = (C0175s) obj;
        return Arrays.equals(this.b, c0175s.b) && this.c == c0175s.c && Intrinsics.areEqual(this.d, c0175s.d) && this.e == c0175s.e && Arrays.equals(this.f, c0175s.f) && this.g == c0175s.g;
    }

    public final int hashCode() {
        return ((Arrays.hashCode(this.f) + ((((this.d.hashCode() + ((UByte$$ExternalSyntheticBackport0.m(this.c) + (Arrays.hashCode(this.b) * 31)) * 31)) * 31) + this.e) * 31)) * 31) + this.g;
    }

    public final String toString() {
        StringBuilder a = AbstractC0057b.a(this.b, new StringBuilder("ConnectResponseMessage(instanceId="), ", status=");
        a.append(this.c);
        a.append(", statusMessage=");
        a.append(this.d);
        a.append(", sessionId=");
        a.append(this.e);
        a.append(", remoteIp=");
        StringBuilder a2 = AbstractC0057b.a(this.f, a, ", remotePort=");
        a2.append(this.g);
        a2.append(')');
        return a2.toString();
    }
}
