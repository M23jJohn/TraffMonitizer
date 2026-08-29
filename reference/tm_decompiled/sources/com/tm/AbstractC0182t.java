package com.tm;

import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.tm.t, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public abstract class AbstractC0182t extends V2 {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AbstractC0182t(EnumC0052a1 type, byte[] instanceId, int i, String host) {
        super(type, instanceId);
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(instanceId, "instanceId");
        Intrinsics.checkNotNullParameter(host, "host");
    }

    public abstract String d();

    public abstract int e();
}
