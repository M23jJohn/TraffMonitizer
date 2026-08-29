package com.tm;

import java.io.OutputStream;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public abstract class V2 extends Z0 {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public V2(EnumC0052a1 type, byte[] instanceId) {
        super(type, instanceId);
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(instanceId, "instanceId");
    }

    @Override // com.tm.Z0
    public void a(OutputStream stream) {
        Intrinsics.checkNotNullParameter(stream, "stream");
        super.a(stream);
        stream.write(AbstractC0099h.a(((C0059b1) this).c));
    }

    @Override // com.tm.Z0
    public byte[] b() {
        return ArraysKt.plus(super.b(), AbstractC0099h.a(c()));
    }

    public abstract int c();
}
