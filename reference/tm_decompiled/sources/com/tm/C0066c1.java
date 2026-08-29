package com.tm;

import java.util.Arrays;
import kotlin.UByte$$ExternalSyntheticBackport0;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.tm.c1, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0066c1 extends X2 {
    public final byte[] b;
    public final boolean c;
    public final String d;
    public final int e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0066c1(byte[] instanceId, boolean z, String statusMessage, int i) {
        super(EnumC0052a1.s, instanceId, z, statusMessage);
        Intrinsics.checkNotNullParameter(instanceId, "instanceId");
        Intrinsics.checkNotNullParameter(statusMessage, "statusMessage");
        this.b = instanceId;
        this.c = z;
        this.d = statusMessage;
        this.e = i;
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

    @Override // com.tm.X2
    public final int e() {
        return this.e;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!Intrinsics.areEqual(C0066c1.class, obj != null ? obj.getClass() : null)) {
            return false;
        }
        if (obj == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.traffmonetizer.sdk.repository.api.command.PacketResponseMessage");
        }
        C0066c1 c0066c1 = (C0066c1) obj;
        return Arrays.equals(this.b, c0066c1.b) && this.c == c0066c1.c && Intrinsics.areEqual(this.d, c0066c1.d) && this.e == c0066c1.e;
    }

    public final int hashCode() {
        return ((this.d.hashCode() + ((UByte$$ExternalSyntheticBackport0.m(this.c) + (Arrays.hashCode(this.b) * 31)) * 31)) * 31) + this.e;
    }

    public final String toString() {
        StringBuilder a = AbstractC0057b.a(this.b, new StringBuilder("PacketResponseMessage(instanceId="), ", status=");
        a.append(this.c);
        a.append(", statusMessage=");
        a.append(this.d);
        a.append(", sessionId=");
        a.append(this.e);
        a.append(')');
        return a.toString();
    }
}
