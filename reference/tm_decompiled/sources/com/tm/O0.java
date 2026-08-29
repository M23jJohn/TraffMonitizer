package com.tm;

import java.util.Arrays;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class O0 extends Z0 {
    public final byte[] b;
    public final String c;
    public final int d;
    public final String e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public O0(byte[] instanceId, String token, String version) {
        super(EnumC0052a1.b, instanceId);
        Intrinsics.checkNotNullParameter(instanceId, "instanceId");
        Intrinsics.checkNotNullParameter(token, "token");
        Intrinsics.checkNotNullParameter(version, "version");
        this.b = instanceId;
        this.c = token;
        this.d = 3;
        this.e = version;
    }

    @Override // com.tm.Z0
    public final byte[] a() {
        return this.b;
    }

    @Override // com.tm.Z0
    public final byte[] b() {
        return ArraysKt.plus(ArraysKt.plus(ArraysKt.plus(super.b(), AbstractC0099h.a(this.c)), AbstractC0099h.a(this.d)), AbstractC0099h.a(this.e));
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!Intrinsics.areEqual(O0.class, obj != null ? obj.getClass() : null)) {
            return false;
        }
        if (obj == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.traffmonetizer.sdk.repository.api.command.HelloMessage");
        }
        O0 o0 = (O0) obj;
        return Arrays.equals(this.b, o0.b) && Intrinsics.areEqual(this.c, o0.c) && this.d == o0.d && Intrinsics.areEqual(this.e, o0.e);
    }

    public final int hashCode() {
        return this.e.hashCode() + ((((this.c.hashCode() + (Arrays.hashCode(this.b) * 31)) * 31) + this.d) * 31);
    }

    public final String toString() {
        StringBuilder a = AbstractC0057b.a(this.b, new StringBuilder("HelloMessage(instanceId="), ", token=");
        a.append(this.c);
        a.append(", deviceType=");
        a.append(this.d);
        a.append(", version=");
        a.append(this.e);
        a.append(')');
        return a.toString();
    }
}
