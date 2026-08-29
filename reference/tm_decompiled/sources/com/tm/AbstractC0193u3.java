package com.tm;

import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.tm.u3, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public abstract class AbstractC0193u3 extends Z0 {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AbstractC0193u3(EnumC0052a1 type, byte[] instanceId, String statusMessage) {
        super(type, instanceId);
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(instanceId, "instanceId");
        Intrinsics.checkNotNullParameter(statusMessage, "statusMessage");
    }

    @Override // com.tm.Z0
    public byte[] b() {
        return ArraysKt.plus(ArraysKt.plus(super.b(), c() ? (byte) 1 : (byte) 0), AbstractC0099h.a(d()));
    }

    public abstract boolean c();

    public abstract String d();
}
