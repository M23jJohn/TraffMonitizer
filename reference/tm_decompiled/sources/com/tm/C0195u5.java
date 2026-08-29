package com.tm;

import kotlin.coroutines.AbstractCoroutineContextElement;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineExceptionHandler;

/* renamed from: com.tm.u5, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0195u5 extends AbstractCoroutineContextElement implements CoroutineExceptionHandler {
    public C0195u5(CoroutineExceptionHandler.Companion companion) {
        super(companion);
    }

    @Override // kotlinx.coroutines.CoroutineExceptionHandler
    public final void handleException(CoroutineContext coroutineContext, Throwable th) {
        boolean z = X0.a;
        X0.a("TraffRepo", "requestsScope error: " + th);
    }
}
