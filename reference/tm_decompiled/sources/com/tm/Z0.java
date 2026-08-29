package com.tm;

import java.io.OutputStream;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public abstract class Z0 {
    public final EnumC0052a1 a;

    public Z0(EnumC0052a1 type, byte[] instanceId) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(instanceId, "instanceId");
        this.a = type;
    }

    public void a(OutputStream stream) {
        Intrinsics.checkNotNullParameter(stream, "stream");
        stream.write(new byte[]{(byte) this.a.a});
        stream.write(a());
    }

    public abstract byte[] a();

    public byte[] b() {
        return ArraysKt.plus(ArraysKt.plus(new byte[0], (byte) this.a.a), a());
    }
}
