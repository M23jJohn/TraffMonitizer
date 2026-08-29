package com.tm;

import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class K0 extends Z0 {
    public final byte[] b;
    public final String c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public K0(byte[] instanceId, String deviceName) {
        super(EnumC0052a1.B, instanceId);
        Intrinsics.checkNotNullParameter(instanceId, "instanceId");
        Intrinsics.checkNotNullParameter(deviceName, "deviceName");
        this.b = instanceId;
        this.c = deviceName;
    }

    @Override // com.tm.Z0
    public final byte[] a() {
        return this.b;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!Intrinsics.areEqual(K0.class, obj != null ? obj.getClass() : null)) {
            return false;
        }
        if (obj == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.traffmonetizer.sdk.repository.api.command.GetDeviceNameResponseMessage");
        }
        K0 k0 = (K0) obj;
        return Arrays.equals(this.b, k0.b) && Intrinsics.areEqual(this.c, k0.c);
    }

    public final int hashCode() {
        return this.c.hashCode() + (Arrays.hashCode(this.b) * 31);
    }

    public final String toString() {
        StringBuilder a = AbstractC0057b.a(this.b, new StringBuilder("GetDeviceNameResponseMessage(instanceId="), ", deviceName=");
        a.append(this.c);
        a.append(')');
        return a.toString();
    }
}
