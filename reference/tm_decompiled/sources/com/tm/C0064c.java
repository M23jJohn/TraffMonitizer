package com.tm;

import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.tm.c, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0064c extends AbstractC0193u3 {
    public final byte[] b;
    public final boolean c;
    public final String d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0064c(byte[] instanceId, boolean z, String statusMessage) {
        super(EnumC0052a1.t, instanceId, statusMessage);
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
        if (!Intrinsics.areEqual(C0064c.class, obj != null ? obj.getClass() : null)) {
            return false;
        }
        if (obj != null) {
            return Arrays.equals(this.b, ((C0064c) obj).b);
        }
        throw new NullPointerException("null cannot be cast to non-null type com.traffmonetizer.sdk.repository.api.command.AcceptReadyResponseMessage");
    }

    public final int hashCode() {
        return Arrays.hashCode(this.b);
    }

    public final String toString() {
        StringBuilder a = AbstractC0057b.a(this.b, new StringBuilder("AcceptReadyResponseMessage(instanceId="), ", status=");
        a.append(this.c);
        a.append(", statusMessage=");
        a.append(this.d);
        a.append(')');
        return a.toString();
    }
}
