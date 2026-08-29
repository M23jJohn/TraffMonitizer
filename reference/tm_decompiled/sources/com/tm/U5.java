package com.tm;

import java.io.IOException;
import kotlin.coroutines.AbstractCoroutineContextElement;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineExceptionHandler;

/* loaded from: classes3.dex */
public final class U5 extends AbstractCoroutineContextElement implements CoroutineExceptionHandler {
    public final /* synthetic */ int a;
    public final /* synthetic */ P1 b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public U5(CoroutineExceptionHandler.Companion companion, int i, P1 p1) {
        super(companion);
        this.a = i;
        this.b = p1;
    }

    @Override // kotlinx.coroutines.CoroutineExceptionHandler
    public final void handleException(CoroutineContext coroutineContext, Throwable th) {
        if (!(th instanceof IOException) || !Intrinsics.areEqual(th.getMessage(), "EndOfStream")) {
            boolean z = X0.a;
            X0.a("UdpProxySession", "[" + this.a + "] error: " + th.getMessage(), th);
        }
        ((H1) this.b).a(this.a, th);
    }
}
