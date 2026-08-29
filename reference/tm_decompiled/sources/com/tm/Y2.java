package com.tm;

import java.util.Arrays;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class Y2 extends Z0 {
    public final byte[] b;
    public final String c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Y2(byte[] instanceId, String deviceName) {
        super(EnumC0052a1.l, instanceId);
        Intrinsics.checkNotNullParameter(instanceId, "instanceId");
        Intrinsics.checkNotNullParameter(deviceName, "deviceName");
        this.b = instanceId;
        this.c = deviceName;
    }

    @Override // com.tm.Z0
    public final byte[] a() {
        return this.b;
    }

    @Override // com.tm.Z0
    public final byte[] b() {
        return ArraysKt.plus(super.b(), AbstractC0099h.a(this.c));
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!Intrinsics.areEqual(Y2.class, obj != null ? obj.getClass() : null)) {
            return false;
        }
        if (obj == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.traffmonetizer.sdk.repository.api.command.SetDeviceNameMessage");
        }
        Y2 y2 = (Y2) obj;
        return Arrays.equals(this.b, y2.b) && Intrinsics.areEqual(this.c, y2.c);
    }

    public final int hashCode() {
        return this.c.hashCode() + (Arrays.hashCode(this.b) * 31);
    }

    public final String toString() {
        StringBuilder a = AbstractC0057b.a(this.b, new StringBuilder("SetDeviceNameMessage(instanceId="), ", deviceName=");
        a.append(this.c);
        a.append(')');
        return a.toString();
    }
}
