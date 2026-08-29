package com.tm;

import kotlin.coroutines.AbstractCoroutineContextElement;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineExceptionHandler;

/* renamed from: com.tm.p1, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0157p1 extends AbstractCoroutineContextElement implements CoroutineExceptionHandler {
    public C0157p1(CoroutineExceptionHandler.Companion companion) {
        super(companion);
    }

    @Override // kotlinx.coroutines.CoroutineExceptionHandler
    public final void handleException(CoroutineContext coroutineContext, Throwable th) {
        boolean z = X0.a;
        X0.a("ProxyBloc", "ProxyException: on [" + coroutineContext + "]: " + th.getMessage(), th);
    }
}
