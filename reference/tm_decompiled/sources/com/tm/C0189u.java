package com.tm;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.tm.u, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0189u extends AbstractC0182t {
    public final byte[] b;
    public final int c;
    public final String d;
    public final int e;
    public final Map f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0189u(byte[] instanceId, int i, String host, int i2, LinkedHashMap features) {
        super(EnumC0052a1.o, instanceId, i, host);
        Intrinsics.checkNotNullParameter(instanceId, "instanceId");
        Intrinsics.checkNotNullParameter(host, "host");
        Intrinsics.checkNotNullParameter(features, "features");
        this.b = instanceId;
        this.c = i;
        this.d = host;
        this.e = i2;
        this.f = features;
    }

    @Override // com.tm.Z0
    public final byte[] a() {
        return this.b;
    }

    @Override // com.tm.V2
    public final int c() {
        return this.c;
    }

    @Override // com.tm.AbstractC0182t
    public final String d() {
        return this.d;
    }

    @Override // com.tm.AbstractC0182t
    public final int e() {
        return this.e;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!Intrinsics.areEqual(C0189u.class, obj != null ? obj.getClass() : null)) {
            return false;
        }
        if (obj == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.traffmonetizer.sdk.repository.api.command.ConnectV2Message");
        }
        C0189u c0189u = (C0189u) obj;
        return Arrays.equals(this.b, c0189u.b) && this.c == c0189u.c && Intrinsics.areEqual(this.d, c0189u.d) && this.e == c0189u.e && Intrinsics.areEqual(this.f, c0189u.f);
    }

    public final int hashCode() {
        return this.f.hashCode() + ((((this.d.hashCode() + (((Arrays.hashCode(this.b) * 31) + this.c) * 31)) * 31) + this.e) * 31);
    }

    public final String toString() {
        StringBuilder a = AbstractC0057b.a(this.b, new StringBuilder("ConnectV2Message(instanceId="), ", sessionId=");
        a.append(this.c);
        a.append(", host=");
        a.append(this.d);
        a.append(", port=");
        a.append(this.e);
        a.append(", features=");
        a.append(this.f);
        a.append(')');
        return a.toString();
    }
}
