package com.tm;

import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.tm.m, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0134m extends Z0 {
    public final byte[] b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0134m(byte[] instanceId) {
        super(EnumC0052a1.c, instanceId);
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
        if (!Intrinsics.areEqual(C0134m.class, obj != null ? obj.getClass() : null)) {
            return false;
        }
        if (obj != null) {
            return Arrays.equals(this.b, ((C0134m) obj).b);
        }
        throw new NullPointerException("null cannot be cast to non-null type com.traffmonetizer.sdk.repository.api.command.ByeMessage");
    }

    public final int hashCode() {
        return Arrays.hashCode(this.b);
    }

    public final String toString() {
        return "ByeMessage(instanceId=" + Arrays.toString(this.b) + ')';
    }
}
