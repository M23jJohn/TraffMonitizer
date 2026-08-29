package com.tm;

import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public abstract class X2 extends AbstractC0193u3 {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public X2(EnumC0052a1 type, byte[] instanceId, boolean z, String statusMessage) {
        super(type, instanceId, statusMessage);
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(instanceId, "instanceId");
        Intrinsics.checkNotNullParameter(statusMessage, "statusMessage");
    }

    @Override // com.tm.AbstractC0193u3, com.tm.Z0
    public byte[] b() {
        return ArraysKt.plus(super.b(), AbstractC0099h.a(e()));
    }

    public abstract int e();
}
