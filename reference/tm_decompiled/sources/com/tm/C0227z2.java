package com.tm;

import kotlin.coroutines.AbstractCoroutineContextElement;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineExceptionHandler;

/* renamed from: com.tm.z2, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public final class C0227z2 extends AbstractCoroutineContextElement implements CoroutineExceptionHandler {
    public final /* synthetic */ J2 a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0227z2(CoroutineExceptionHandler.Companion companion, J2 j2) {
        super(companion);
        this.a = j2;
    }

    @Override // kotlinx.coroutines.CoroutineExceptionHandler
    public final void handleException(CoroutineContext coroutineContext, Throwable th) {
        boolean z = X0.a;
        X0.a("ServiceBloc", "onReadyFailed", th);
        this.a.add(new L2(EnumC0123k2.READY_FAILED));
    }
}
