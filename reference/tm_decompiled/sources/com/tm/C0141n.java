package com.tm;

import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.tm.n, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0141n extends AbstractC0193u3 {
    public final byte[] b;
    public final boolean c;
    public final String d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0141n(byte[] instanceId, boolean z, String statusMessage) {
        super(EnumC0052a1.r, instanceId, statusMessage);
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
        if (!Intrinsics.areEqual(C0141n.class, obj != null ? obj.getClass() : null)) {
            return false;
        }
        if (obj != null) {
            return Arrays.equals(this.b, ((C0141n) obj).b);
        }
        throw new NullPointerException("null cannot be cast to non-null type com.traffmonetizer.sdk.repository.api.command.ByeResponseMessage");
    }

    public final int hashCode() {
        return Arrays.hashCode(this.b);
    }

    public final String toString() {
        StringBuilder a = AbstractC0057b.a(this.b, new StringBuilder("ByeResponseMessage(instanceId="), ", status=");
        a.append(this.c);
        a.append(", statusMessage=");
        a.append(this.d);
        a.append(')');
        return a.toString();
    }
}
