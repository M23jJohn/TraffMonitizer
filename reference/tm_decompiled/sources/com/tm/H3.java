package com.tm;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class H3 {
    public C0116j2 a;
    public X1 b;

    public final Object a(Z0 z0, Continuation continuation) {
        C0116j2 c0116j2 = this.a;
        if (c0116j2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sender");
            c0116j2 = null;
        }
        Object send = c0116j2.d.send(z0, continuation);
        if (send != IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            send = Unit.INSTANCE;
        }
        return send == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? send : Unit.INSTANCE;
    }
}
