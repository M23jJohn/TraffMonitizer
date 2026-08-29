package com.tm;

import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.tm.j, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0113j extends Z0 {
    public final byte[] b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0113j(byte[] instanceId) {
        super(EnumC0052a1.j, instanceId);
        Intrinsics.checkNotNullParameter(instanceId, "instanceId");
        this.b = instanceId;
    }

    @Override // com.tm.Z0
    public final byte[] a() {
        return this.b;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!Intrinsics.areEqual(C0113j.class, obj != null ? obj.getClass() : null)) {
            return false;
        }
        if (obj != null) {
            return Arrays.equals(this.b, ((C0113j) obj).b);
        }
        throw new NullPointerException("null cannot be cast to non-null type com.traffmonetizer.sdk.repository.api.command.BalanceMessage");
    }

    public final int hashCode() {
        return Arrays.hashCode(this.b);
    }

    public final String toString() {
        return "BalanceMessage(instanceId=" + Arrays.toString(this.b) + ')';
    }
}
