package com.tm;

import kotlin.coroutines.AbstractCoroutineContextElement;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineExceptionHandler;

/* loaded from: classes3.dex */
public final class M extends AbstractCoroutineContextElement implements CoroutineExceptionHandler {
    public final /* synthetic */ X a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public M(CoroutineExceptionHandler.Companion companion, X x) {
        super(companion);
        this.a = x;
    }

    @Override // kotlinx.coroutines.CoroutineExceptionHandler
    public final void handleException(CoroutineContext coroutineContext, Throwable th) {
        boolean z = X0.a;
        X0.a("Connection", "socketError: " + th);
        if (th instanceof C0054a3) {
            this.a.add(new C0089f3(th));
        } else if (th instanceof M0) {
            this.a.add(new N0(th));
        } else {
            this.a.add(new C0082e3(th));
        }
    }
}
