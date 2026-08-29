package com.tm;

import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class H0 extends V2 {
    public final byte[] b;
    public final int c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public H0(byte[] instanceId, int i) {
        super(EnumC0052a1.h, instanceId);
        Intrinsics.checkNotNullParameter(instanceId, "instanceId");
        this.b = instanceId;
        this.c = i;
    }

    @Override // com.tm.Z0
    public final byte[] a() {
        return this.b;
    }

    @Override // com.tm.V2
    public final int c() {
        return this.c;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!Intrinsics.areEqual(H0.class, obj != null ? obj.getClass() : null)) {
            return false;
        }
        if (obj != null) {
            return Arrays.equals(this.b, ((H0) obj).b);
        }
        throw new NullPointerException("null cannot be cast to non-null type com.traffmonetizer.sdk.repository.api.command.EofMessage");
    }

    public final int hashCode() {
        return Arrays.hashCode(this.b);
    }

    public final String toString() {
        StringBuilder a = AbstractC0057b.a(this.b, new StringBuilder("EofMessage(instanceId="), ", sessionId=");
        a.append(this.c);
        a.append(')');
        return a.toString();
    }
}
