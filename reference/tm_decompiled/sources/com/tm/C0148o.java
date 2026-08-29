package com.tm;

import java.util.Arrays;
import kotlin.UByte$$ExternalSyntheticBackport0;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.tm.o, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0148o {
    public final String a;
    public final boolean b;
    public final byte[] c;
    public final String d;
    public final int e;
    public final String f;
    public final boolean g;
    public final String h;
    public final EnumC0127l i;
    public final boolean j;
    public final String k;

    public C0148o(String token, boolean z, byte[] instanceId, String balancerUrl, int i, String serverUrl, boolean z2, String sdkVersion, EnumC0127l buildType, boolean z3, String str) {
        Intrinsics.checkNotNullParameter(token, "token");
        Intrinsics.checkNotNullParameter(instanceId, "instanceId");
        Intrinsics.checkNotNullParameter(balancerUrl, "balancerUrl");
        Intrinsics.checkNotNullParameter(serverUrl, "serverUrl");
        Intrinsics.checkNotNullParameter(sdkVersion, "sdkVersion");
        Intrinsics.checkNotNullParameter(buildType, "buildType");
        this.a = token;
        this.b = z;
        this.c = instanceId;
        this.d = balancerUrl;
        this.e = i;
        this.f = serverUrl;
        this.g = z2;
        this.h = sdkVersion;
        this.i = buildType;
        this.j = z3;
        this.k = str;
    }

    public static C0148o a(C0148o c0148o, String str, boolean z, byte[] bArr, int i, String str2, boolean z2, EnumC0127l enumC0127l, boolean z3, String str3, int i2) {
        String token = (i2 & 1) != 0 ? c0148o.a : str;
        boolean z4 = (i2 & 2) != 0 ? c0148o.b : z;
        byte[] instanceId = (i2 & 4) != 0 ? c0148o.c : bArr;
        String balancerUrl = c0148o.d;
        int i3 = (i2 & 16) != 0 ? c0148o.e : i;
        String serverUrl = (i2 & 32) != 0 ? c0148o.f : str2;
        boolean z5 = (i2 & 64) != 0 ? c0148o.g : z2;
        String sdkVersion = c0148o.h;
        EnumC0127l buildType = (i2 & 256) != 0 ? c0148o.i : enumC0127l;
        boolean z6 = (i2 & 512) != 0 ? c0148o.j : z3;
        String str4 = (i2 & 1024) != 0 ? c0148o.k : str3;
        c0148o.getClass();
        Intrinsics.checkNotNullParameter(token, "token");
        Intrinsics.checkNotNullParameter(instanceId, "instanceId");
        Intrinsics.checkNotNullParameter(balancerUrl, "balancerUrl");
        Intrinsics.checkNotNullParameter(serverUrl, "serverUrl");
        Intrinsics.checkNotNullParameter(sdkVersion, "sdkVersion");
        Intrinsics.checkNotNullParameter(buildType, "buildType");
        return new C0148o(token, z4, instanceId, balancerUrl, i3, serverUrl, z5, sdkVersion, buildType, z6, str4);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!Intrinsics.areEqual(C0148o.class, obj != null ? obj.getClass() : null)) {
            return false;
        }
        if (obj == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.traffmonetizer.sdk.repository.config.Config");
        }
        C0148o c0148o = (C0148o) obj;
        return Intrinsics.areEqual(this.a, c0148o.a) && this.b == c0148o.b && Arrays.equals(this.c, c0148o.c) && Intrinsics.areEqual(this.d, c0148o.d) && this.e == c0148o.e && Intrinsics.areEqual(this.f, c0148o.f) && this.g == c0148o.g && Intrinsics.areEqual(this.h, c0148o.h) && this.i == c0148o.i && this.j == c0148o.j && Intrinsics.areEqual(this.k, c0148o.k);
    }

    public final int hashCode() {
        int m = (UByte$$ExternalSyntheticBackport0.m(this.j) + ((this.i.hashCode() + ((this.h.hashCode() + ((UByte$$ExternalSyntheticBackport0.m(this.g) + ((this.f.hashCode() + ((((this.d.hashCode() + ((Arrays.hashCode(this.c) + ((UByte$$ExternalSyntheticBackport0.m(this.b) + (this.a.hashCode() * 31)) * 31)) * 31)) * 31) + this.e) * 31)) * 31)) * 31)) * 31)) * 31)) * 31;
        String str = this.k;
        return m + (str != null ? str.hashCode() : 0);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Config(token=");
        sb.append(this.a);
        sb.append(", wifiOnly=");
        sb.append(this.b);
        sb.append(", instanceId=");
        StringBuilder a = AbstractC0057b.a(this.c, sb, ", balancerUrl=");
        a.append(this.d);
        a.append(", apiPort=");
        a.append(this.e);
        a.append(", serverUrl=");
        a.append(this.f);
        a.append(", verboseLogging=");
        a.append(this.g);
        a.append(", sdkVersion=");
        a.append(this.h);
        a.append(", buildType=");
        a.append(this.i);
        a.append(", serviceActive=");
        a.append(this.j);
        a.append(", appVersion=");
        a.append(this.k);
        a.append(')');
        return sb.toString();
    }
}
