package com.tm;

import java.io.OutputStream;
import java.util.Arrays;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.tm.b1, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0059b1 extends V2 {
    public final byte[] b;
    public final int c;
    public final byte[] d;
    public final long e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0059b1(byte[] instanceId, int i, byte[] packet, long j) {
        super(EnumC0052a1.d, instanceId);
        Intrinsics.checkNotNullParameter(instanceId, "instanceId");
        Intrinsics.checkNotNullParameter(packet, "packet");
        this.b = instanceId;
        this.c = i;
        this.d = packet;
        this.e = j;
    }

    @Override // com.tm.Z0
    public final byte[] a() {
        return this.b;
    }

    @Override // com.tm.V2, com.tm.Z0
    public final byte[] b() {
        return ArraysKt.plus(ArraysKt.plus(super.b(), AbstractC0099h.a(this.d.length)), this.d);
    }

    @Override // com.tm.V2
    public final int c() {
        return this.c;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!Intrinsics.areEqual(C0059b1.class, obj != null ? obj.getClass() : null)) {
            return false;
        }
        if (obj == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.traffmonetizer.sdk.repository.api.command.PacketMessage");
        }
        C0059b1 c0059b1 = (C0059b1) obj;
        return Arrays.equals(this.b, c0059b1.b) && Arrays.equals(this.d, c0059b1.d);
    }

    public final int hashCode() {
        return Arrays.hashCode(this.d) + (Arrays.hashCode(this.b) * 31);
    }

    public final String toString() {
        return "PacketMessage(instanceId=" + this.b + ", sessionId=" + this.c + ", ts=[" + this.e + "] packet=[" + this.d.length + " bytes])";
    }

    @Override // com.tm.V2, com.tm.Z0
    public final void a(OutputStream stream) {
        Intrinsics.checkNotNullParameter(stream, "stream");
        super.a(stream);
        stream.write(AbstractC0099h.a(this.d.length));
        stream.write(this.d);
    }
}
