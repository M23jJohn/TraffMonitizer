package com.tm;

import java.util.Arrays;
import kotlin.UByte$$ExternalSyntheticBackport0;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class Z2 extends AbstractC0193u3 {
    public final byte[] b;
    public final boolean c;
    public final String d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Z2(byte[] instanceId, boolean z, String statusMessage) {
        super(EnumC0052a1.A, instanceId, statusMessage);
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
        if (!Intrinsics.areEqual(Z2.class, obj != null ? obj.getClass() : null)) {
            return false;
        }
        if (obj == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.traffmonetizer.sdk.repository.api.command.SetDeviceNameResponseMessage");
        }
        Z2 z2 = (Z2) obj;
        return Arrays.equals(this.b, z2.b) && this.c == z2.c && Intrinsics.areEqual(this.d, z2.d);
    }

    public final int hashCode() {
        return this.d.hashCode() + ((UByte$$ExternalSyntheticBackport0.m(this.c) + (Arrays.hashCode(this.b) * 31)) * 31);
    }

    public final String toString() {
        StringBuilder a = AbstractC0057b.a(this.b, new StringBuilder("SetDeviceNameResponseMessage(instanceId="), ", status=");
        a.append(this.c);
        a.append(", statusMessage=");
        a.append(this.d);
        a.append(')');
        return a.toString();
    }
}
